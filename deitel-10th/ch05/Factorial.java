// Ex 5.13: Factorials up to 20.

public class Factorial {
	public static void main(String[] args) {
		long fac = 1L;

		System.out.printf("%2s\t%s%n", "N", "N!");
		for (int i = 1; i <= 20; i++) {
			System.out.printf("%2d\t%d%n", i, fac);
			fac *= (long) i;
		}
	}
}
