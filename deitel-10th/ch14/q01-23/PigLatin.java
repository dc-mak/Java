// Ex 14.7: Convert a sentence to Pig Latin.

import java.util.Scanner;

public final class PigLatin {
	public static void main(String[] args){
		 Scanner input = new Scanner(System.in);
		 System.out.println("Enter sentence to translate to Pig Latin:");
		 String[] tokens = input.nextLine().split("\\s+");
		 for (String s : tokens)
			 printLatinWord(s);
		 System.out.println();
	}

	// I could use a mathcer and what not but overkill.
	private static void printLatinWord(String s) {
		if (s.length() < 2|| !s.matches("\\w*")) {
			System.out.print(s+" ");
			return;
		}
		System.out.print(s.replaceAll("(\\w)(\\w*)", "$2$1ay "));
	}
}
