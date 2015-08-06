// Ex 5.20: Approximate Pi to 200,000 terms.

public class PiApprox {
	public static void main(String[] args) {
		double pi = 4;
		System.out.println("Term\tApprox");
		int i;
		for (i = 2; i <= 200000 || (pi < 3.14159) ; i++) {
			if (i % 2 == 0)
				pi -= 4.0 / (2.0 * (double) i - 1.0);
			else
				pi += 4.0 / (2.0 * (double) i - 1.0);
			if (i % 1000 == 0)
				System.out.printf("%3d\t%f%n", i, pi);
		}
		System.out.printf("%3d\t%f%n", i, pi);
	}
}
