// Ex 4.31: Converting binary number to decimal.
//			There is always the radix option in nextInt()...

import java.util.Scanner;

public class Binary {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter binary number: ");
		int inVal = input.nextInt();
		int total=0, pow2=1;

		while (inVal > 0) {
			int digit = inVal % 10;

			if (digit == 1)
				total += pow2;
			else if (digit != 0) {
				System.out.println("Not a binary number!");
				inVal = total = -1;
			}

			pow2 *= 2;
			inVal /= 10;
		}
		System.out.printf("Decimal is %d%n", total);
	}
}
