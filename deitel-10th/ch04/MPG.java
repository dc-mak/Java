// Ex 4.17: Miles per gallon per trip and overall.
//			This is the sort of ugliness you get without logical operators,
//			breaks and returns.

import java.util.Scanner;

public class MPG {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int miles_t=0, gall_t=0; // Totals

		int miles = getNextInt("miles travelled in", input);

		if (miles < 1) // End program
			System.out.println("Insufficient input.");
		else {

			int gall = getNextInt("gallons of fuel used on ", input);

			if (gall < 1) // End program
				System.out.println("Insufficient input.");
			else {

				miles_t += miles;
				gall_t  += gall;
				showResults(miles, gall, miles_t, gall_t);

				while (gall > 0) {

					miles = getNextInt("miles travelled in", input);

					if (miles < 1) // Set termination condition.
						gall = 0;
					else {

						gall = getNextInt("gallons of fuel used on ", input);

						if (gall > 0) {
							miles_t += miles;
							gall_t  += gall;
							showResults(miles, gall, miles_t, gall_t);
						} // else loop termination is set.
					}
				}
			}
		}
	}

	private static int getNextInt(String msg, Scanner input) {
		System.out.printf ("Enter %s trip(0 or less to end program): ", msg);
		return input.nextInt();
	}

	private static void showResults(int miles, int gall, int miles_t, int gall_t) {
		System.out.printf ("This trip:\t%.2fmpg%n",(double) miles / gall);
		System.out.printf ("Overall:\t%.2fmpg%n",(double) miles_t / gall_t);
	}
}
