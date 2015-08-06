// Ex 5.11: Find the smallest of several integers.

import java.util.Scanner;

public class Min {
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);

		System.out.print("Enter number of integers to be compared: ");
		int count = input.nextInt();

		System.out.print("Enter number: ");
		int min = input.nextInt();
		for (int i = 1; i < count; i ++) {
			System.out.print("Enter number: ");
			int j = input.nextInt();
			if (j < min)
				min = j;
		}

		System.out.printf("Minimum is %d%n", min);
	}
}
