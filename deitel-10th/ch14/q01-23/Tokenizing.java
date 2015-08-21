// Ex 14.8: Tokenizing an American phone number.

import java.util.Scanner;

public final class Tokenizing {
	public static void main(String[] args){
		final Scanner input = new Scanner(System.in);
		final String number = input.nextLine();
		if (number.matches("\\(\\d{3}\\) \\d{3}-\\d{4}")) {
			String[] tokens = number.split(" ");
			System.out.println("Area: "+tokens[0].substring(1,4));
			tokens = tokens[1].split("-");
			System.out.println("Phone: "+tokens[0]+tokens[1]);
		}
	}
}
