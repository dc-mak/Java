// Ex 17.11: A quick Invoice class because one didn't come with he examples.

public final class Invoice {
	private final int PartNumber;
	private final String PartDescription;
	private final int Quantity;
	private final double Price;

	public int getPartNumber() { return PartNumber; }
	public String getPartDescription() { return PartDescription; }
	public int getQuantity() { return Quantity; }
	public double getPrice() { return Price; }

	public Invoice(int PartNumber, String PartDescription,
					int Quantity, double Price) {
		this.PartNumber = PartNumber;
		this.PartDescription = PartDescription;
		this.Quantity = Quantity;
		this.Price = Price;
	}

	@Override
	public String toString() {
		return String.format("%03d  %-15s  %03d  %6.2f",
				PartNumber, PartDescription, Quantity, Price);
	}
}
