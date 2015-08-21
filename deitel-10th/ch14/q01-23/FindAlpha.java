// Ex 14.11: Find the number of occurences of each letter in a given string.

import java.util.Arrays;
import java.util.Scanner;

public final class FindAlpha {
	public static void main(String[] args) {
		final Scanner input = new Scanner(System.in);
		System.out.print("Enter string: ");
		final String phrase = input.nextLine().toLowerCase();
		final int[] count = new int[26];
		Arrays.fill(count, 0);
		
		for (char c = 'a'; c <= 'z'; c++) {
			int indx = phrase.indexOf(c);
			while (indx != -1 && indx < phrase.length()) {
				count[c - 'a']++;
				indx = phrase.indexOf(c, indx+1);
			}
		}

		for (int i = 0; i < 26; i++)
			System.out.println(((char) ('a'+i)) + ": " + count[i]);
	}
}

// char[] chars = input.nextLine().toCharArray();
// for (char c : chars) {
// 	final int indx = (int) (Character.toLowerCase(c) - 'a');
// 		if (0 <= indx && indx < 26)
// 			count[indx]++;
// }

