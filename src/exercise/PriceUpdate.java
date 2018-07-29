package exercise;

import com.google.common.base.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PriceUpdate)) return false;
		PriceUpdate that = (PriceUpdate) o;
		return Double.compare(that.getPrice(), getPrice()) == 0 &&
				Objects.equal(getCompanyName(), that.getCompanyName());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getCompanyName(), getPrice());
	}
}
