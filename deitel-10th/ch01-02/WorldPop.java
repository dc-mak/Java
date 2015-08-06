// 2.34: Wordl population growth rate calculator.

import java.util.Scanner;

public class WorldPop {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Please enter current world population: ");
		int pop = input.nextInt();

		System.out.print("Please enter annual world population growth rate: ");
		int rate = input.nextInt();

		System.out.printf("Wold population after");
		System.out.printf("1 yr:  %d%n", pop*rate);
		System.out.printf("2 yrs: %d%n", pop*rate*rate);
		System.out.printf("3 yrs: %d%n", pop*rate*rate*rate);
		System.out.printf("4 yrs: %d%n", pop*rate*rate*rate*rate);
		System.out.printf("5 yrs: %d%n", pop*rate*rate*rate*rate*rate);
	}
}
