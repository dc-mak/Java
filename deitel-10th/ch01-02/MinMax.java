// Ex 2.24: Read in 5 integers and return the min and max.

import java.util.Scanner;

public class MinMax {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter five integers: ");

		// First int is automatically the min and the max.
		int max = input.nextInt();
		int min = max;

		// 2nd int
		int next = input.nextInt();
		if (next < min)
			min = next;

		if (max < next)
			max = next;

		// 3rd
		next = input.nextInt();
		if (next < min)
			min = next;

		if (max < next)
			max = next;

		// 4th
		next = input.nextInt();
		if (next < min)
			min = next;

		if (max < next)
			max = next;

		// 5th
		next = input.nextInt();
		if (next < min)
			min = next;

		if (max < next)
			max = next;

		System.out.printf("Min: %d and Max: %d%n", min, max);
	}
}
