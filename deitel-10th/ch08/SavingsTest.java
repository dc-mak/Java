// Ex 8.6: Savings.java test.

public class SavingsTest {
	public static void main(String[] args) {
		Savings s1 = new Savings(2000.0);
		Savings s2 = new Savings(3000.0);

		Savings.monthlyInterest(0.04);
		System.out.printf("Savings 1: %.2f.\tSavings 2: %.2f%n",
							s1.monthlyInterest(), s2.monthlyInterest());

		Savings.monthlyInterest(0.05);
		System.out.printf("Savings 1: %.2f.\tSavings 2: %.2f%n",
							s1.monthlyInterest(), s2.monthlyInterest());
	}
}
