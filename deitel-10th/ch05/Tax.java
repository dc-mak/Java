// Ex 5.32: Fair tax calc.

import java.util.Scanner;

public class Tax {
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);

		double total = 0.0;
		double rate  = 0.3;

		total += getPosDouble(input, "housing");
		total += getPosDouble(input, "food");
		total += getPosDouble(input, "clothing");
		total += getPosDouble(input, "transportation");
		total += getPosDouble(input, "education");
		total += getPosDouble(input, "health care");
		total += getPosDouble(input, "vacations");

		System.out.printf("You would have to pay approximately £%.2f tax.%n",
							total * rate);
	}

	private static double getPosDouble(Scanner input, String categ) {
		System.out.printf("How much do you spend on %s? ", categ);
		double inVal = input.nextDouble();
		while (inVal < 0.0) {
			System.out.println("Positive amount please.");
			System.out.print("Enter amount spent of %s: ");
			inVal = input.nextDouble();
		}
		return inVal;
	}
}
