package exercise;

import java.util.Objects;

public class PriceUpdate
{
    private final String companyName;

    private final double price;

    public PriceUpdate(String companyName, double price)
    {
        this.companyName = companyName;
        this.price = price;
    }

    public String getCompanyName()
    {
        return this.companyName;
    }

    public double getPrice()
    {
        return this.price;
    }

    @Override
    public String toString()
    {
        return getCompanyName() + " - " + getPrice();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof PriceUpdate)) {
            return false;
        }
        
        return this.toString().equals(obj.toString());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getCompanyName(), getPrice());
    }
}
