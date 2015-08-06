// Ex 5.17: Caculating sales.

import java.util.Scanner;

public class Sales {
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);

		double price1 = 2.98;
		double price2 = 4.50;
		double price3 =	9.98;
		double price4 = 4.49;
		double price5 = 6.87;

		double total = 0.0;

		System.out.print ("Select product number(0 or less to quit): ");
		int inVal = input.nextInt();
		while (inVal > 0) {
			if (inVal <= 5) {
				System.out.printf("Enter quantity of product %d: ", inVal);
				int quantity = getPosInt (input);
				switch (inVal) {
					case 1: total += price1 * (double) quantity;
							break;
					case 2: total += price2 * (double) quantity;
							break;
					case 3: total += price3 * (double) quantity;
							break;
					case 4: total += price4 * (double) quantity;
							break;
					case 5: total += price5 * (double) quantity;
				}
			} else
				System.out.println ("Product number between 1 and 5(inclusive) only.");
			System.out.print ("Select product number(0 or less to quit): ");
			inVal = input.nextInt();
		}

		System.out.printf("Total is %.2f%n", total);
	}

	private static int getPosInt(Scanner input) {
		int inVal = input.nextInt();
		while (inVal < 0) {
			System.out.print("Positive quantity only: ");
			inVal = input.nextInt();
		}
		return inVal;
	}
}
