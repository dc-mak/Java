// Ex 2.30: Seperating out the digits in a five digit character.

import java.util.Scanner;

public class Separate {
	public static void main(String[] args) {

		System.out.print("Enter 5 digit number: ");
		Scanner input = new Scanner(System.in);
		int a = input.nextInt();

		int a5 = a % 10;
		a = a / 10;

		int a4 = a % 10;
		a = a / 10;

		int a3 = a % 10;
		a = a / 10;


		int a2 = a % 10;
		a = a / 10;

		int a1 = a % 10;

		System.out.printf("%d %d %d %d %d%n", a1, a2, a3, a4, a5);
	}
}
