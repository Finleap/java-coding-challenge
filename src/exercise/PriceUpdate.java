package exercise;

public class PriceUpdate {

    private final String companyName;
    private final double price;

    public PriceUpdate(String companyName, double price) {
        this.companyName = companyName;
        this.price = price;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return companyName + " - " + price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if(!PriceUpdate.class.isAssignableFrom(obj.getClass())){
            return false;
        }
        PriceUpdate priceUpdate = (PriceUpdate) obj;
        if ((this.companyName == null) ? (priceUpdate.companyName != null) :
                !this.companyName.equals(priceUpdate.companyName)) {
            return false;
        }
        if (this.price != priceUpdate.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 55 * hash + (this.companyName != null ? this.companyName.hashCode() : 0);
        hash = 55 * hash + (int)Double.doubleToLongBits(this.price);
        return hash;
    }
}
