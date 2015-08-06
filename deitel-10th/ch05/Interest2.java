// Ex 5.18: Use integers only!

public class Interest2 {
	public static void main(String args[]) {
		int amount = 100000;
		int rate_num = 105;
		int rate_den = 100;

		System.out.printf("%s%20s%n", "Year", "Amount on deposit");

		for (int year = 1; year <= 10; year++) {
			amount = amount * rate_num / rate_den;
			System.out.printf("%4d%,20d.%2d%n", year, amount / 100, amount % 100);
		}
	}
}
