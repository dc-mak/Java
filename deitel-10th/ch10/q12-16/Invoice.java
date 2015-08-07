// Ex 10.14/15

public class Invoice implements Payable {
	private final String partNumber;
	private final String partDescription;
	private int quantity;
	private double pricePerItem;

	public Invoice(String partNumber, String partDescription, int quantity,
			double pricePerItem) {
		if (quantity < 0)
			throw new IllegalArgumentException("Quantity must be >= 0");
		if (pricePerItem < 0.0)
			throw new IllegalArgumentException(
					"Price per item must be >= 0");
		this.quantity = quantity;
		this.partNumber = partNumber;
		this.partDescription = partDescription;
		this.pricePerItem = pricePerItem;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public String getPartDescription() {
		return partDescription;
	}

	public void setQuantity(int quantity) {
		if (quantity < 0)
			throw new IllegalArgumentException("Quantity must be >= 0");
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setPricePerItem(double pricePerItem) {
		if (pricePerItem < 0.0)
			throw new IllegalArgumentException(
					"Price per item must be >= 0");
		this.pricePerItem = pricePerItem;
	}

	public double getPricePerItem() {
		return pricePerItem;
	}

	@Override
	public String toString() {
		return String.format("%s: %n%s: %s (%s) %n%s: %d %n%s: $%,.2f",
				"invoice", "part number", getPartNumber(), getPartDescription(),
				"quantity", getQuantity(), "price per item", getPricePerItem());
	}

	@Override
	public double getPaymentAmount() {
		return getQuantity() * getPricePerItem();
	}
}


