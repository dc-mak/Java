// Ex 5.15) Drawing patterns!

public class Patterns {
	public static void main(String[] args) {

		// row number = number of stars
		for (int row = 1; row <= 10; row++) {
			for (int col = 1; col <= row; col++) {
				System.out.print('*');
			}
			System.out.println();
		}

		System.out.println();

		// row number = number of stars
		for (int row = 10; row >= 1; row--) {
			for (int col = 1; col <= row; col++) {
				System.out.print('*');
			}
			System.out.println();
		}

		System.out.println();

		// row number = number of spaces
		for (int row = 0; row <= 9; row++) {
			for (int col = 1; col <= 10; col++) {
				if (col <= row)
					System.out.print(' ');
				else
					System.out.print('*');
			}
			System.out.println();
		}

		System.out.println();

		// row number = number of spaces
		for (int row = 9; row >= 0; row--) {
			for (int col = 1; col <= 10; col++) {
				if (col <= row)
					System.out.print(' ');
				else
					System.out.print('*');
			}
			System.out.println();
		}
	}
}
