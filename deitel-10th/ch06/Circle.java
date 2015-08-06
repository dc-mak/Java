// Ex 6.20: Ask for the radius of a sphere and return the volume.

public class Circle {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		System.out.print("Enter radius of circle: ");
		double radius = input.nextDouble();
		while (radius < 0) {
			System.out.print("Please enter positive radius: ");
			radius = input.nextDouble();
		}
		System.out.println ("Area is " + circleArea(radius) + ".");
	}
	private static double circleArea(double radius) {
		return Math.PI * radius * radius;
	}
}
