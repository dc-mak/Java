// Ex 7.20: Sales slip Class.

public class SalesSlip {
	public static final int PEOPLE = 4;
	public static final int PRODS  = 5;

	private final int person;
	private final int product;
	private final double value;

	public SalesSlip(int person, int product, double value) {
		this.person = 1 <= person && person <= PEOPLE ? person : 0;
		this.product = 1 <= product && product <= PRODS? product : 0;
		this.value = value > 0.0 ? value : 0.0;
	}

	public int getPerson()   { return person; }
	public int getProduct()  { return product; }
	public double getValue() { return value; }
}
