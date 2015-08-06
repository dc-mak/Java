// Ex 4.32: Checkerboard of asterisks.

public class Checkerboard {
	public static void main(String[] args) {
		int limit = 8;
		int row = 1;

		while (row <= limit) {
			if (row % 2 == 0)
				System.out.print(" ");

			int col = 1;
			while (col < limit) {
				System.out.print(" *");
				col++;
			}

			System.out.printf(" *%n");
			row++;
		}
	}
}
