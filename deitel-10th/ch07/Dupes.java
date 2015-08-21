// Ex 7.12: Duplicate elimination of 5 integers

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Dupes {
	private static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {

		final int LIMIT = 5;

		// Method 1
		int[] vals2 = new int[1];
		System.out.print("Enter number: ");
		vals2[0] = getInt();
		for (int i = 1; i < LIMIT; i++) {
			System.out.print("Enter number: ");
			int x = getInt();
			vals2 = uniqueIns(vals2, x);
		}

		System.out.printf("Unique values given are: %s.%n",
						   Arrays.toString(vals2));
		
		// Method 2
		final ArrayList<Integer> vals = new ArrayList<>();
		for (int i = 0; i < LIMIT; i++) {
			System.out.print("Enter number: ");
			int x = getInt();
			if (!vals.contains(x))
				vals.add(x);
		}
		System.out.printf("Unique values given are: %s.%n", vals.toString());
	}

	public static int[] uniqueIns(int[] vals, int x) {
		for (int v : vals)
			if (v == x)
				return vals;
		
		// Slow/wasteful, sure, but if the imperative is smallest array possible.
		int[] vals2 = Arrays.copyOf(vals, vals.length+1);
		vals2[vals2.length-1] = x;
		return vals2;
	}

	public static int getInt() {
		return getPosInt(10, 100, "Number between 10 and 100 only.");
	}

	public static int getPosInt(int lower, int upper, String msg) {
		int x = input.nextInt();
		while (x < lower || upper < x) {
			System.out.println(msg);
			x = input.nextInt();
		}
		return x;
	}
}
