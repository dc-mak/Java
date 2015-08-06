// Ex. 2.6: Take three integers as an input and return their product.
import java.util.Scanner;

public class Prod {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int x, y, z;

		System.out.print("Enter first integer: ");
		x = input.nextInt();

		System.out.print("Enter second integer: ");
		y = input.nextInt();

		System.out.print("Enter third integer: ");
		z = input.nextInt();

		int result = x*y*z;
		System.out.printf("Product is %d %n%r", result);
	}
}
