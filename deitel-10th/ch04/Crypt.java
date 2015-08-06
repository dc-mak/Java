// Ex 4.38: Encrypt a 4-digit number. Arrays, booleans and lambdas... I miss
//			thee *sigh*.

import java.util.Scanner;

public class Crypt {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int inVal, dig4, dig3, dig2, dig1;
		inVal = dig4 = dig3 = dig2 = dig1 = 0;
		int exit = 0;
		// Get the 4 digits
		System.out.print("Enter 4 digit number to encrypt: ");
		inVal = getPosInt(input);

		dig4 = inVal % 10;
		inVal /= 10;

		dig3 = inVal % 10;
		inVal /= 10;

		dig2 = inVal % 10;
		inVal /= 10;

		dig1 = inVal;

		// Now encrypt them
		dig1 =(dig1 + 7) % 10;
		dig2 =(dig2 + 7) % 10;
		dig3 =(dig3 + 7) % 10;
		dig4 =(dig4 + 7) % 10;

		System.out.printf("%d%d%d%d%n", dig1, dig2, dig3, dig4);

		// I'm supposed to do this in a separate app but assed.
		exit = 0;
		System.out.print("Enter 4 digit number to encrypt: ");
		inVal = getPosInt(input);

		dig4 = inVal % 10;
		inVal /= 10;

		dig3 = inVal % 10;
		inVal /= 10;

		dig2 = inVal % 10;
		inVal /= 10;

		dig1 = inVal;

		dig1 = decrypt(dig1);
		dig2 = decrypt(dig2);
		dig3 = decrypt(dig3);
		dig4 = decrypt(dig4);

		System.out.printf("%d%d%d%d%n", dig1, dig2, dig3, dig4);
	}

	private static int getPosInt(Scanner input) {
		int inVal = input.nextInt();
		while (inVal < 0) {
			System.out.println("Positive integer please.");
			inVal = input.nextInt();
		}
		return inVal;
	}

	private static int decrypt(int dig) {
		if (dig >= 7)
			dig -= 7;
		else
			dig += 3;
		return dig;
	}
}
