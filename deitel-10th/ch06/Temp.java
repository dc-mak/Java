// Ex 6.22: Convert between celsius and farenheit.

import java.util.Scanner;

public class Temp {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Input temperature: ");
		double temp = input.nextDouble();

		System.out.println("Assuming Farenheit, Celsius is " + toCel(temp));
		System.out.println("Assuming Celsius, Farenheit is " + toFar(temp));
	}
	private static double toCel(double temp) { return 1.8*temp + 32.0; }
	private static double toFar(double temp) { return 5.0/9.0*(temp - 32.0); }
}
