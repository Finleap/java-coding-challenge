package exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Scheduler Thread to limit number of stocks sent to consumer per second ad batch
 */
public class StockUpdateScheduler extends Thread {

    private final Consumer consumer;
    private final int MAX_PRICE_UPDATES;
    private static List<PriceUpdate> priceUpdates;

    StockUpdateScheduler(Consumer consumer, List<PriceUpdate> priceUpdates, int maxPriceUpdates) {
        this.consumer = consumer;
        this.priceUpdates = priceUpdates;
        this.MAX_PRICE_UPDATES = maxPriceUpdates;
    }

    @Override
    public void run() {
        try {
            issueStockUpdatesPerSecond();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    /**
     *  Invoke Certain number of stock per second
     * @throws InterruptedException
     */
    private void issueStockUpdatesPerSecond() throws InterruptedException {
        List<PriceUpdate> stockUpdatedListWithDuplication = new ArrayList<>();
        do {
            stockUpdatedListWithDuplication.addAll(priceUpdates);

            List<PriceUpdate> latestStockUpdates = StockUtils
                    .removeOldUpdatesFromBulk(stockUpdatedListWithDuplication);

            latestStockUpdates = (latestStockUpdates.size() > MAX_PRICE_UPDATES) ?
                    latestStockUpdates.subList(0, MAX_PRICE_UPDATES - 1) : latestStockUpdates;

            if (!latestStockUpdates.isEmpty()) {
                consumer.send(latestStockUpdates);
            }

            stockUpdatedListWithDuplication.clear();
            latestStockUpdates.clear();
            priceUpdates.clear();

            Thread.sleep(1000);
        } while (!priceUpdates.isEmpty());
    }

}
