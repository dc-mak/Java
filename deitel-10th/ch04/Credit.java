// Ex 4.18: Check to see whether the new balance on a customer's charge
//  		account exceeds their credit limit.

import java.util.Scanner;

public class Credit {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Enter account number(0 or less to exit): ");
		int account = input.nextInt();
		while (account > 0) {
			System.out.print("Enter account balance: ");
			int balance = input.nextInt();

			System.out.print("Enter charges for month: ");
			int charge = input.nextInt();

			System.out.print("Enter credits for month: ");
			int credit = input.nextInt();

			System.out.print("Enter credit limit: ");
			int limit  = input.nextInt();

			int newBal = balance + charge - credit;
			System.out.printf("New balance: %d%n", newBal);
			if (newBal > limit)
				System.out.println("Credit limit exceeded.");

			System.out.print("Enter next account number(0 or less to exit): ");
			account = input.nextInt();
		}
	}
}
