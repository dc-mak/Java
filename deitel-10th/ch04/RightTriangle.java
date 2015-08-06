// Ex 4.36: Sides of a Right Triangle.

import java.util.Scanner;

public class RightTriangle {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter first side: ");
		double inVal = getPosDouble("first", input);
		double side1 = inVal * inVal;

		System.out.print("Enter second side: ");
		inVal = getPosDouble("second", input);
		double side2 = inVal * inVal;

		System.out.print("Enter third side: ");
		inVal = getPosDouble("third", input);
		double side3 = inVal * inVal;

		// This is what no booleans does to a person.
		if (side1 + side2 == side3)
			System.out.println("Right!");
		else if (side1 + side3 == side2)
			System.out.println("Right!");
		else if (side2 + side3 == side1)
			System.out.println("Right!");
		else
			System.out.println("Not right-angled");
	}

	private static double getPosDouble(String msg, Scanner input) {
		double inVal = input.nextDouble();
		while (inVal < 0.0) {
			System.out.print("Positive side please.");
			System.out.printf("Enter %s side: ", msg);
			inVal = input.nextDouble();
		}
		return inVal;
	}
}
