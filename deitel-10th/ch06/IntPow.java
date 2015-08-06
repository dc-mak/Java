// Ex 6.14: Integer exponentiation.

import java.util.Scanner;
public class IntPow {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter base: ");
		int base = input.nextInt();
		System.out.print("Enter exponent: ");
		int exp = input.nextInt();
		while (exp < 0) {
			System.out.print("Positive exponent: ");
			exp = input.nextInt();
		}
		System.out.println(integerPower(base, exp));
	}
	private static int integerPower (int base, int exp) {
		int total = 1;
		while (exp > 0) {
			total *= base;
			exp--;
		}
		return total;
	}
}
