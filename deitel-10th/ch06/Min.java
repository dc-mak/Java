// Ex 6.23: Fin minimum of 3 digits.

import java.util.Scanner;

public class Min {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int num1 = input.nextInt();

		System.out.print("Enter a number: ");
		int num2 = input.nextInt();

		System.out.print("Enter a number: ");
		int num3 = input.nextInt();

		System.out.println ("Minimum is " + min(num1, num2, num3));
	}
	private static int min(int a, int b, int c) {
		return Math.min (Math.min (a,b), c);
	}
}
