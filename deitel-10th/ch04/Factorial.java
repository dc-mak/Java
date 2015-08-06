// Ex 4.37: This a good way to estimate e! Gonna get underflow.

import java.util.Scanner;

public class Factorial {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter x: ");
		double x = input.nextDouble();

		System.out.print("Enter number of terms for approximation: ");
		int limit = input.nextInt();

		int count = 1;
		int fact  = 1;

		double total = 1.0;
		double powx  = 1.0;

		while (count <= limit) {
			fact *= count;
			powx *= x;
			total += powx /(double) fact;
			count++;
		}
		System.out.printf("e is approximately %.4f%n", total);
	}
}
