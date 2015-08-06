// Ex 6.10: Rounding numbers to specific decimal places:

import java.util.Scanner;

public class Round {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter number to round (0 to quit): ");
		double inVal = input.nextDouble();
		while (inVal != 0.0) {
			System.out.println(inVal);
			System.out.println(roundToInteger(inVal));
			System.out.println(roundToTenths(inVal));
			System.out.println(roundToHundredths(inVal));
			System.out.println(roundToThousandths(inVal));
			System.out.print("Enter number to round (0 to quit): ");
			inVal = input.nextDouble();
		}
	}
	private static double roundToInteger (double x) { return Math.floor(x+0.5); }
	private static double roundToTenths(double x) { return Math.floor(x*10+0.5)/10; }
	private static double roundToHundredths(double x) { return Math.floor(x*100+0.5)/100; }
	private static double roundToThousandths(double x) { return Math.floor(x*1000+0.5)/1000; }
}
