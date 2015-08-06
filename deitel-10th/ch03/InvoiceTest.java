// Ex 3.12: Testing the Invoice.java example.

public class InvoiceTest {
	public static void main(String[] args) {

		Invoice invoice = new Invoice("09K", "Screws", 4, 0.2);

		System.out.println(invoice.getNumber());
		invoice.setNumber("09J");
		System.out.println(invoice.getNumber());

		System.out.println(invoice.getDescription());
		invoice.setDescription("Long screws");
		System.out.println(invoice.getDescription());

		System.out.printf("Quantity: %d%n", invoice.getQuantity());
		invoice.setQuantity(-1);
		System.out.printf("Quantity: %d%n", invoice.getQuantity());
		invoice.setQuantity(0);
		System.out.printf("Quantity: %d%n", invoice.getQuantity());
		invoice.setQuantity(1);
		System.out.printf("Quantity: %d%n", invoice.getQuantity());

		System.out.printf("Price: %.2f%n", invoice.getPrice());
		invoice.setPrice(-1.0);
		System.out.printf("Price: %.2f%n", invoice.getPrice());
		invoice.setPrice(0.0);
		System.out.printf("Price: %.2f%n", invoice.getPrice());
		invoice.setPrice(1.0);
		System.out.printf("Price: %.2f%n", invoice.getPrice());

		System.out.printf("Invoice amount: %.2f%n", invoice.getInvoiceAmount());

		Invoice invoice2 = new Invoice("09K", "Screws", -4, -0.2);
		Invoice invoice3 = new Invoice("09K", "Screws", 0, 0.0);

		System.out.printf("Quantity: %d%n", invoice2.getQuantity());
		System.out.printf("Price: %.2f%n", invoice2.getPrice());
		System.out.printf("Quantity: %d%n", invoice3.getQuantity());
		System.out.printf("Price: %.2f%n", invoice3.getPrice());
	}
}
