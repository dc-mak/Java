// Ex 14.6: Writing own versions of indexOf and lastIndexOf.

import java.util.Scanner;

public final class IndexOf {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter pattern: ");
		final String pattern = input.nextLine();
		System.out.print("Enter string: ");
		final String search = input.nextLine();
		System.out.println("Naive: "+naiveIndex(search, pattern));
		System.out.println("KMP: "+indexOf(search, pattern));
		System.out.println("Reverse KMP: "+lastIndexOf(search, pattern));
	}

	public static int naiveIndex(String s, String p) {
		if (p.length() > s.length())
			return -1;
		final char[] search  = s.toCharArray();
		final char[] pattern = p.toCharArray();

		int i;
		boolean match = false;
		for (i = 0; i < search.length - pattern.length; i++) {
			match = true;
			for (int j = 0; j < pattern.length; j++)
				if (search[j+i] != pattern[j]) {
					match = false;
					break;
				}

			if (match) 
				return i;
		}
		return -1;
	}

	public static int indexOf (String s, String p) {
		if (p.length() > s.length())
			return -1;

		final char[] search  = s.toCharArray();
		final char[] pattern = p.toCharArray();

		for (char c : search)
			if (c > 127)
				return -1;
		for (char c : pattern)
			if (c > 127)
				return -1;

		final int RADIX = 128;
		final int[][] dfa = new int[RADIX][pattern.length];
		dfa[pattern[0]][0] = 1;
		for (int x = 0, j = 1; j < pattern.length; j++) {
			for (int c = 0; c < RADIX; c++)
				dfa[c][j] = dfa[c][x];

			dfa[pattern[j]][j] = j+1;
			x = dfa[pattern[j]][x];
		}

		int i,j;
		for (i = j = 0; i < search.length && j < pattern.length; i++)
			j = dfa[search[i]][j];

		if (j == pattern.length)
			return i - pattern.length;
		else
			return -1;
	}

	public static int lastIndexOf(String search, String pattern) {
		// Yes, I could do this from scratch, but
		final StringBuilder s = (new StringBuilder(search)).reverse();
		final StringBuilder p = (new StringBuilder(pattern)).reverse();

		int i = indexOf(s.toString(), p.toString());
		if (i == -1)	
			return -1;
		return search.length() - pattern.length() - i;
	}
}
