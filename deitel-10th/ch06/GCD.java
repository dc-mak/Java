// Ex 6.16/17: Integer exponentiation.

import java.util.Scanner;
public class GCD {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter first number: ");
		int m = getPosInt(input);
		System.out.print("Enter second number: ");
		int n = getPosInt(input);
		System.out.println(gcd(m,n));
	}

	private static int getPosInt(Scanner input) {
		int x = input.nextInt();
		while (x <= 0) {
			System.out.print("Positive integer only: ");
			x = input.nextInt();
		}
		return x;
	}

	private static int gcd(int m, int n) {
		while (m != 0) {
			int m_ = m;
			m = n % m;
			n = m_;
		}
		return n;
	}
}
