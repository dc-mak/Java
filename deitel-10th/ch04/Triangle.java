// Ex 4.35: could three give non-zero sides represent a triangle?

import java.util.Scanner;

public class Triangle {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter first side: ");
		int largest = getPosInt("first", input);

		System.out.print("Enter second side: ");
		int inVal = getPosInt("second", input);

		int second;
		if (inVal > largest) {
			second  = largest;
			largest = inVal;
		} else
			second = inVal;

		System.out.print("Enter third side: ");
		inVal = getPosInt("third", input);

		int third;
		if (inVal > largest) {
			third = largest;
			largest = inVal;
		} else
			third = inVal;

		if (largest > second + third)
			System.out.println("Not possible to construct a triangle.");
		else
			System.out.println("Triangle");
	}

	private static int getPosInt(String msg, Scanner input) {
		int inVal = input.nextInt();
		while(inVal < 0) {
			System.out.println("Positivie integer please.");
			System.out.printf("Enter %s side: ", msg);
			inVal = input.nextInt();
		}
		return inVal;
	}
}
