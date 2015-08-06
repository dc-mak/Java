// Ex 2.35: Carpool savings calculator.

import java.util.Scanner;

public class CarPool {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int dist, cost, mileage, parking, tolls;

		System.out.print("Enter total miles driven every day: ");
		dist = input.nextInt();

		System.out.print("Enter cost per litre of fuel: ");
		cost = input.nextInt();

		System.out.print("Enter mileage per litre of fuel: ");
		mileage = input.nextInt();

		System.out.print("Enter daily parking fees: ");
		parking = input.nextInt();

		System.out.print("Enter tolls paid every day: ");
		tolls = input.nextInt();

		System.out.printf("You could save £%d every day by car-pooling.%n",
							cost*dist/mileage + parking + tolls);
	}
}
