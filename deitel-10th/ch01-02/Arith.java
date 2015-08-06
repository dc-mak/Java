// Ex 2.15: Enter two integers and return sum, difference, product and quotient.

import java.util.Scanner;

public class Arith {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Enter first integer: ");
		int a = input.nextInt();

		System.out.print("Enter second integer: ");
		int b = input.nextInt();

		System.out.printf("Sum: %d%nDifference: %d%nProduct: %d%nQuotient: %d%n", a+b, a-b, a*b, a/b);
	}
}
