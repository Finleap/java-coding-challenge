package exercise;

import java.util.*;

public class LoadHandler {

    private static List<PriceUpdate> priceUpdates;

    public LoadHandler(Consumer consumer) {
        priceUpdates = new ArrayList<>();
        new StockUpdateScheduler(consumer, priceUpdates,
                100).start();
    }

    public void receive(PriceUpdate priceUpdate) {
        priceUpdates.add(priceUpdate);
    }

}
