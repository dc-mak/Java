// 2.16: Take two integers and return the largest of the two.

import java.util.Scanner;

public class Compare {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Enter first integer: ");
		int a = input.nextInt();

		System.out.print("Enter second integer: ");
		int b = input.nextInt();

		if (a == b)
			System.out.println("These numbers are equal.");

		if (a > b)
			System.out.printf("%d is larger%n", a);

		if (a < b)
			System.out.printf("%d is larger%n", b);
	}
}
