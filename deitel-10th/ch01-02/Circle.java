// Ex 2.28: Diameter, circumference and area of a circle given radius.

import java.util.Scanner;

public class Circle {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Enter floating point number: ");
		double r = input.nextDouble();

		System.out.printf("Diameter: %f%nCircumference: %f%nArea: %f%n", 2.0*r, 2*Math.PI*r, Math.PI*r*r);
	}
}
