// Ex 5.24 and 5.25: Make a Diamond.
//					 The alternative to using Maths.abs was as follows
//					 for (int row = 0; row < size; row++)
//						int spaces = row<half ? half-row : row-half;
//						int stars  = row<half ? 2*row+1  : size - 2*(row-half);

import java.util.Scanner;

public class Diamond {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter diamond size: ");
		int size = getPosInt(input);
		int half = size/2;
		for (int row = -half; row <= half; row++) {
			int spaces = Math.abs(row);
			int stars  = size-2*Math.abs(row);

			for (int col = 0; col < spaces; col++)
				System.out.print(' ');
			for (int col = 0; col < stars ; col++)
				System.out.print('*');
			for (int col = 0; col < spaces; col++)
				System.out.print(' ');

			System.out.print('\n');
		}
	}

	private static int getPosInt(Scanner input) {
		int inVal = input.nextInt();
		while (inVal < 1 || 79 < inVal || inVal % 2 == 0) {
			System.out.println ("An odd number between 1 and 19(inclusive) only, please.");
			System.out.print("Enter number: ");
			inVal = input.nextInt();
		}
		return inVal;
	}
}
