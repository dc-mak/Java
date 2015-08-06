// Ex 4.19:	Test harness for Sales.java

import java.util.Scanner;

public class SalesTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Sales items = new Sales();
		double total = items.getSalary();

		System.out.print("Enter quantity of item 1 sold: ");
		total += nextVal(input) * items.getPrice1() * items.getComission();

		System.out.print("Enter quantity of item 2 sold: ");
		total += nextVal(input) * items.getPrice2() * items.getComission();

		System.out.print("Enter quantity of item 3 sold: ");
		total += nextVal(input) * items.getPrice3() * items.getComission();

		System.out.print("Enter quantity of item 4 sold: ");
		total += nextVal(input) * items.getPrice4() * items.getComission();

		System.out.printf("Total commission is: %.2f.%n", total);
	}

	private static int nextVal(Scanner input) {
		int quantity = input.nextInt();
		return quantity < 0 ? 0 : quantity;
	}

}
