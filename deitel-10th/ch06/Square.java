// Ex 6.18: Display square.

import java.util.Scanner;
public class Square {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter number: ");
		int size = input.nextInt();
		while (size <= 0) {
			System.out.print("Enter positve integer: ");
			size = input.nextInt();
		}
		System.out.print("Enter fillcharacter: ");
		char fill = input.next().charAt(0);
		drawSquare(size, fill);
	}
	private static void drawSquare(int size, char fill) {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++)
				System.out.print(fill);
			System.out.println();
		}
	}
}
