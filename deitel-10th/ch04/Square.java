// Ex 4.29: Square of asterisks. I hate doing this with while loops.

import java.util.Scanner;

public class Square {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter size of square to create: ");
		int limit = input.nextInt();
		int row   = 1;
		while (row <= limit) {
			int col = 1;
			while (col <= limit) {
				if (col == 1)
					System.out.print("*");
				else if (col == limit)
					System.out.printf("*%n");
				else
					System.out.printf(" ");
				col++;
			}
			row++;
		}
	}
}
