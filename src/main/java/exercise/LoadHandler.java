package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class LoadHandler
{
    private static final int MAX_PRICE_UPDATES = 100;

    private Map<String, PriceUpdate> companyPrices = new ConcurrentHashMap<>();

    private final Consumer consumer;

    public LoadHandler(Consumer consumer)
    {
        this.consumer = consumer;
        createScheduleSender();
    }

    public void receive(PriceUpdate priceUpdate)
    {
        if (companyPrices.size() < MAX_PRICE_UPDATES) {
            companyPrices.put(priceUpdate.getCompanyName(), priceUpdate);
        }
    }

    private void createScheduleSender()
    {
        Timer timerObj = new Timer(true);
        timerObj.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (companyPrices.values().size() > 0) {
                    consumer.send(new ArrayList<>(companyPrices.values()));
                    companyPrices.clear();
                }
            }
        }, 0, 1000);
    }
}
