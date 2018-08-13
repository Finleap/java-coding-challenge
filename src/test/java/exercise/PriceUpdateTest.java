package exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PriceUpdateTest
{
    @Test
    public void equals_should_return_true_for_equal_objects()
    {
       PriceUpdate priceUpdate1 = new PriceUpdate("Company1", 0.1);
       PriceUpdate priceUpdate2 = new PriceUpdate("Company1", 0.1);

        assertEquals(priceUpdate1, priceUpdate2);
    }

    @Test
    public void equals_should_return_true_if_check_same_object()
    {
        PriceUpdate priceUpdate = new PriceUpdate("Company1", 0.1);

        assertEquals(priceUpdate, priceUpdate);
    }

    @Test
    public void equals_should_return_false_if_check_different_instance()
    {
        PriceUpdate priceUpdate = new PriceUpdate("Company1", 0.1);

        assertNotEquals(priceUpdate, new Object());
    }

    @Test
    public void equals_should_return_false_for_unequal_objects()
    {
        PriceUpdate priceUpdate1 = new PriceUpdate("Company1", 0.1);
        PriceUpdate priceUpdate2 = new PriceUpdate("Company1", 0.2);

        assertNotEquals(priceUpdate1, priceUpdate2);
    }
}
