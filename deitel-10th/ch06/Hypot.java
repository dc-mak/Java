// Ex 6.15: Calculate the hypotenuse of right-angled triange given the two other sides.

import java.util.Scanner;
public class Hypot {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter side 1: ");
		double side1 = input.nextDouble();
		while (side1 < 0.0) {
			System.out.print("Positive length: ");
			side1 = input.nextDouble();
		}
		System.out.print("Enter side 2: ");
		double side2 = input.nextDouble();
		while (side2 < 0.0) {
			System.out.print("Positive length: ");
			side2 = input.nextDouble();
		}
		System.out.println(hypotenuse(side1, side2));
	}
	private static double hypotenuse(double side1, double side2) {
		return Math.sqrt(side1*side1+side2*side2);
	}
}
