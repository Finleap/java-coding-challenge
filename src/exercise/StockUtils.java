package exercise;

import java.util.ArrayList;
import java.util.List;

public class StockUtils {

    /**
     * drop old updates from the list
     * @param duplicatedData
     * @return {@list} of Updated Stock Data
     */
   public static List<PriceUpdate> removeOldUpdatesFromBulk(List<PriceUpdate> duplicatedData) {
        List<PriceUpdate> updatedStocks = new ArrayList<>();
        duplicatedData.stream().forEach(update -> {
            if (updatedStocks.stream().anyMatch(o -> o.getCompanyName().equals(update.getCompanyName()))) {
                updatedStocks.stream()
                        .filter(producer -> producer.getCompanyName().equals(update.getCompanyName()))
                        .findFirst()
                        .map(p -> {
                            updatedStocks.remove(p);
                            return p;
                        });
                updatedStocks.add(update);
            } else if (!updatedStocks.contains(update)) {
                updatedStocks.add(update);
            }
        });
        return updatedStocks;
    }
}
