// Ex 6.21: Separate out digits in a 4 digit number.

import java.util.Scanner;

public class Digits {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter 4 digit number: ");
		int num = input.nextInt();
		while (num < -9999 || 9999 < num) {
			System.out.print("Enter 4 digit number: ");
			num = input.nextInt();
		}
		displayDigits(num);

	}
	private static void displayDigits(int num) {
		int dig4 = num % 10;
		num /= 10;

		int dig3 = num % 10;
		num /= 10;

		int dig2 = num % 10;
		num /= 10;

		System.out.printf("Digits: %d %d %d %d%n", num, dig2, dig3, dig4);
	}
}
