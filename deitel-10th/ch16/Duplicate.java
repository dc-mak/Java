// Ex 16.13: Duplicate elimination of first names.

import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collection;

public final class Duplicate {
	public static void main(String[] args){
		final Scanner input = new Scanner(System.in);
		final Set<String> firstNames = new HashSet<>();
		System.out.print("Enter first names:\n? ");
		String resp;
		while (!(resp = input.next()).equals("q")) {
			firstNames.add(resp);
			System.out.print("? ");
		}

		System.out.print("Enter name to search for:\n? ");
		System.out.printf("Name is %sin set.%n", 
			firstNames.contains(input.next()) ? "" : "not ");
	}
}
