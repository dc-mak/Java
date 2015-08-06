// Ex 6.8: Parking charge calculator.

import java.util.Scanner;

public class Parking {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.printf("Enter hours parked: ");
		double hours = input.nextDouble();
		double total = 0.0;
		while (hours > 0.0) {
			double charge = calculateCharges(hours);
			System.out.printf("Charge is £%.2f%n", charge);
			total += charge;
			System.out.printf("Total so far: £%.2f%n", total);
			System.out.println("Enter hours parked: ");
			hours = input.nextDouble();
		}
	}
	private static double calculateCharges(double hours) {
		double base = 2.00;
		double min_stay = 3.00;
		double rate = 0.5;
		double total = base + (hours > min_stay ? rate * Math.ceil(hours - min_stay) : 0.0);
		return total > 10.0 ? 10.0 : total;
	}
}
