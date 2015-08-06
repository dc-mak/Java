// Ex 3.12:	Invoice for an example item sold at a hardware store.

public class Invoice {

	private String number;
	private String description;
	private int	quantity;
	private double price;

	public Invoice(String number, String description,
					int quantity, double price) {

		this.number = number;
		this.description = description;

		if (quantity < 0)
			this.quantity = 0;
		if (quantity >= 0)
			this.quantity = quantity;

		if (price <= 0.0)
			this.price = 0.0;
		if (quantity > 0.0)
			this.price = price;
	}

	public String getNumber() { return number; }
	public void setNumber(String number) { this.number = number; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) {
		if (quantity < 0)
			this.quantity = 0;
		if (quantity >= 0)
			this.quantity = quantity;
	}

	public double getPrice() { return price; }
	public void setPrice(double price) {
		if (price <= 0.0)
			this.price = 0.0;
		if (quantity > 0.0)
			this.price = price;
	}

	// Quantity is auto-cast to double.
	public double getInvoiceAmount() { return price * quantity; }

}
