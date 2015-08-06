// Ex 4.30: Determine if a 5-digit integer is a palindrome.
//			I want my booleans and logical operators!

import java.util.Scanner;

public class Palindromes {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int exit = 0;
		while (exit == 0) {
			System.out.print("Enter 5 digit number: ");
			int inVal = input.nextInt();

			int dig5 = inVal % 10;
			inVal /= 10;

			int dig4 = inVal % 10;
			inVal /= 100;

			// No need to store middle digit

			int dig2 = inVal % 10;
			inVal /= 10;

			int dig1 = inVal;

			if (dig1 == 0) {
				System.out.println("Please enter a 5 digit number.");
				exit = 0;
			} else {
				if (dig1 == dig5) {
					if (dig2 == dig4)
						System.out.println("Palindrome");
				} else
					System.out.println("Not a palindrome.");
				exit = 1;
			}
		}
	}
}
