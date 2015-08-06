// Ex 4.19: A list of sales items and commission value.

public class Sales {
	private double commission = 0.09;
	private double salary = 200.0;
	private double item1 = 239.99,
				   item2 = 129.75,
				   item3 = 99.95,
				   item4 = 350.89;

	public double getComission() { return commission; }
	public double getSalary() { return salary; }

	public double getPrice1() { return item1; }
	public double getPrice2() { return item2; }
	public double getPrice3() { return item3; }
	public double getPrice4() { return item4; }
}
