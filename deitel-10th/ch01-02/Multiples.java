// Test if the first integer is a multiple of the second.

import java.util.Scanner;

public class Multiples {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Enter first integer: ");
		int a = input.nextInt();

		System.out.print("Enter second integer: ");
		int b = input.nextInt();

		if (a % b == 0)
			System.out.println("First is a multiple of the second.");
		if (a % b != 0)
			System.out.println("First is a not a multiple of the second.");
	}
}
