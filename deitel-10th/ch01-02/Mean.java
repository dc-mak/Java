// 2.17: Take three integers and return the sum, mean, product, min and max.

import java.util.Scanner;

public class Mean {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Enter first integer: ");
		int a = input.nextInt();

		System.out.print("Enter second integer: ");
		int b = input.nextInt();

		System.out.print("Enter third integer: ");
		int c = input.nextInt();

		System.out.printf("Sum: %d%nMean: %d%nProduct: %d%n", a+b+c, (a+b+c)/c, a*b*c);

		int max = a;
		if (b > a)
			max = b;
		if (c > max)
			max = c;

		int min = a;
		if (b < a)
			min = b;
		if (c < min)
			min = c;

		System.out.printf("Min: %d%nMax: %d%n", min, max);
	}
}
