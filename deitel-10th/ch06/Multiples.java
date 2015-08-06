// Ex 6.16/17: Integer exponentiation.

import java.util.Scanner;
public class Multiples {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter first number: ");
		int first = input.nextInt();
		System.out.print("Enter second number: ");
		int second = input.nextInt();
		System.out.println(isMultiple(first,second));

		System.out.print("Enter number: ");
		first = input.nextInt();
		while (first !=0) {
			System.out.println(isEven(first));
			System.out.print("Enter number: ");
			first = input.nextInt();
		}
	}
	private static boolean isMultiple (int first, int second) {
		return (first == 0 ? false : second % first == 0);
	}
	private static boolean isEven (int num) { return isMultiple(2, num); }
}
