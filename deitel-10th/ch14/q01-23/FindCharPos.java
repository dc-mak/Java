// Ex 14.11: Find character in string.

import java.util.Scanner;

public final class FindCharPos {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter string: ");
		String string = input.nextLine();
		System.out.print("Enter character: ");
		char letter = input.nextLine().charAt(0);
		System.out.println("Index: "+string.indexOf(letter));
	}
}
