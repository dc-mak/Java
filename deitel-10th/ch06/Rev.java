// Ex 6.26: Reverse digits of number.

import java.util.Scanner;
public class Rev {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter number: ");
		int num = input.nextInt();
		while (num != 0) {
			System.out.print(num % 10);
			num /= 10;
		}
		System.out.println();
	}
}
