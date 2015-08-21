// Recursive search:

import java.util.Random;
import java.util.Arrays;

public final class Search {
	public static void main(String[] args){
		if (args.length < 1)
			return;

		final int[] test = new Random().ints(20, -20, 41).toArray();
		System.out.printf("Index: %d in array %n%s%n",
			indexOf(test, Integer.parseInt(args[0])), Arrays.toString(test));

		final char[] chars = Arrays.toString(test).toCharArray();
		reverse(chars);
		System.out.println();
		System.out.println("minimum: |"+minimum(chars)+"|");
	}

	private static int indexOf(int[] arr, int x) { return indOf(arr, 0, x) ; }
	private static int indOf(int[] arr, int ind, int x) {
		if (ind == arr.length)
			return -1;

		if (arr[ind] == x)
			return ind;
		else
			return indOf(arr, ind+1, x);
	}

	private static void reverse(char[] chars) { rev(chars, chars.length-1); }

	private static void rev(char[] chars, int ind) {
		if (ind >= 0) {
			System.out.print(chars[ind]);
			rev(chars, ind-1);
		}
	}

	private static char minimum(char[] chars) { return min(chars, chars[0], 1); }

	private static char min(char[] chars, char minc, int ind) {
		if (ind > chars.length-1)
			return minc;
		if (minc < chars[ind])
			return min(chars, minc, ind+1);
		else
			return min(chars, chars[ind], ind+1);
	} 
}
