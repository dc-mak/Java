// Ex 6.6: Ask for the radius of a sphere and return the volume.

public class Sphere {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		System.out.print("Enter radius of sphere: ");
		double radius = input.nextDouble();
		while (radius < 0) {
			System.out.print("Please enter positive radius: ");
			radius = input.nextDouble();
		}
		System.out.println ("Volume is " + sphereVolume(radius) + ".");
	}
	private static double sphereVolume(double radius) {
		return (4.0 / 3.0) * Math.PI * Math.pow(radius,3.0);
	}
}
