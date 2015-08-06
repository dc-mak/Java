// Ex 6.32: Distance between two points.

import java.util.Scanner;

public class Dist {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a x1: ");
		double x1 = input.nextDouble();

		System.out.print("Enter a y1: ");
		double y1 = input.nextDouble();

		System.out.print("Enter a x2: ");
		double x2 = input.nextDouble();

		System.out.print("Enter a y2: ");
		double y2 = input.nextDouble();

		System.out.printf("Distance is %.3f.%n", dist(x1, y1, x2, y2));
	}
	private static double dist(double x1, double y1,
							   double x2, double y2) {
		return Math.sqrt(Math.pow (x2-x1, 2.0) + Math.pow (y2-y1, 2.0));
	}
}
