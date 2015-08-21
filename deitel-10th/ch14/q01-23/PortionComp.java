// Ex 14.4: Comparing portion of two string input by the user.

import java.util.Scanner;
public final class PortionComp {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter start index of comparison: ");
		final int start = input.nextInt();
		System.out.print("Enter number of characters to be compared: ");
		final int end = input.nextInt();
		System.out.print("Enter first string: ");
		String s1 = input.next();
		System.out.print("Enter second string: ");
		String s2 = input.next();
		if (s1.regionMatches(true, start, s2, start, start+end))
			System.out.println("First is equal region equals (ignoring case) to second.");
		else
			System.out.println("First isn't equal region equals (ignoring case) to second.");
	}
}
