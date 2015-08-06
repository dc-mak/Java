// 2.32: Counts the number of negative, positive and zero integers input.

import java.util.Scanner;

public class NegPosZero {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int negs, pos, zeros, next;
		negs = pos = zeros = 0;

		System.out.println("Please input 5 integers:");

		// 1st
		next = input.nextInt();
		if (next < 0)
			negs = negs + 1;
		if (next == 0)
			zeros = zeros + 1;
		if (next > 0)
			pos = pos + 1;

		// 2nd
		next = input.nextInt();
		if (next < 0)
			negs = negs + 1;
		if (next == 0)
			zeros = zeros + 1;
		if (next > 0)
			pos = pos + 1;

		// 3rd
		next = input.nextInt();
		if (next < 0)
			negs = negs + 1;
		if (next == 0)
			zeros = zeros + 1;
		if (next > 0)
			pos = pos + 1;

		// 4th
		next = input.nextInt();
		if (next < 0)
			negs = negs + 1;
		if (next == 0)
			zeros = zeros + 1;
		if (next > 0)
			pos = pos + 1;

		// 5th
		next = input.nextInt();
		if (next < 0)
			negs = negs + 1;
		if (next == 0)
			zeros = zeros + 1;
		if (next > 0)
			pos = pos + 1;

		System.out.printf("Negs: %d%nPos: %d%nZeros: %d%n", negs, pos, zeros);
	}
}
