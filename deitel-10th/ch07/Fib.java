// Ex 7.29: Fibonnaci series.

import java.util.Scanner;

public class Fib {
	public static final Scanner input = new Scanner(System.in);

	public static void main(String[] args){
		System.out.print("Enter number: ");
		double n = getPos();
		n = fib(n);
		System.out.println("Fibonnaci number is: "+n+".");
	}

	public static double fib(double n) {
		if (n <= 1)
			return n;
		double first = 0;
		double secnd = 1;
		while (n > 1) {
			secnd += first;
			first  = secnd - first;
			n--;
		}
		return secnd;
	}

	public static double getPos() {
		double n = input.nextDouble();
		while (n < 0) {
			System.out.println("Non-negative integers only.");
			n = input.nextDouble();
		}
		return n;
	}
}
