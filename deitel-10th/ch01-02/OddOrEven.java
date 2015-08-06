// 2.25: Tests whether an input integer is odd or even.

import java.util.Scanner;

public class OddOrEven {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter an integer: ");
		int a = input.nextInt();

		if (a % 2 == 0)
			System.out.println("Even");
		if (a % 2 == 1)
			System.out.println("Odd");
	}
}
