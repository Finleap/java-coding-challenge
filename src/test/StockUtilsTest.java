package test;

import exercise.PriceUpdate;
import exercise.StockUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class StockUtilsTest {

    private List<PriceUpdate> priceUpdates;
    @Before
    public void setUp() throws Exception {
        priceUpdates=new ArrayList<>();
        priceUpdates.add(new PriceUpdate("Apple", 97.85));
        priceUpdates.add(new PriceUpdate("Facebook", 91.66));
        priceUpdates.add(new PriceUpdate("Google", 160.73));
        priceUpdates.add(new PriceUpdate("Facebook", 91.71));
        priceUpdates.add(new PriceUpdate("Google", 160.76));
        priceUpdates.add(new PriceUpdate("Apple", 97.89));
        priceUpdates.add(new PriceUpdate("Google", 160.75));
        priceUpdates.add(new PriceUpdate("Facebook", 91.62));
        priceUpdates.add(new PriceUpdate("Google", 160.71));
    }

    @Test
    public void filterOldStockToDisplayLatestUpdate() {
        List<PriceUpdate> stocks = StockUtils.removeOldUpdatesFromBulk(priceUpdates);
        ArrayList<PriceUpdate> newPriceUpdates = new ArrayList<>();
        newPriceUpdates.add(new PriceUpdate("Apple", 97.89));
        newPriceUpdates.add(new PriceUpdate("Facebook", 91.62));
        newPriceUpdates.add(new PriceUpdate("Google", 160.71));

        Assert.assertEquals(stocks.size(),3);
        Assert.assertEquals(newPriceUpdates,stocks);
    }
}