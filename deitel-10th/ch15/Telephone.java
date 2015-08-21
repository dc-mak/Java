// Ex 15.7: Words from a give telephone number.

import java.io.FileNotFoundException;
import java.io.PrintStream;

public final class Telephone {
	public static void main(String[] args){
		if (args.length < 1 || args[0].matches("\\d*(0|1)\\d*") ||
			!args[0].matches("\\d\\d\\d\\d\\d\\d\\d"))
			return;

		try (PrintStream output = new PrintStream("telephone.txt")) {
			final char[][] letters = {{'A', 'B', 'C'}, {'D', 'E', 'F'}, 
									  {'G', 'H', 'I'}, {'J', 'K', 'L'}, 
									  {'M', 'N', 'O'}, {'P', 'R', 'S'}, 
									  {'T', 'U', 'V'}, {'W', 'X', 'Y'}};
			final int[] count   = {0, 1, 2};
			final char[][] nums = new char[7][3];
			for (int i = 0; i < 7; i++)
				nums[i] = letters[args[0].charAt(i) - '0' - 2];

			// I miss recursion.
			for (int a : count)
			for (int b : count)
			for (int c : count)
			for (int d : count)
			for (int e : count)
			for (int f : count)
			for (int g : count)
				output.printf("%c%c%c%c%c%c%c%n", nums[0][a], nums[1][b],
					nums[2][c], nums[3][d], nums[4][e], nums[5][f], nums[6][g]);

		} catch (SecurityException e) {
			System.err.println("Write permission denied. Terminating");
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.err.println("Error opening file. Terminating");
			System.exit(1);
		}
	}
}
