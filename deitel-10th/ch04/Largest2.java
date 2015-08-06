// Ex 4.21: Largest of 10 numbers.
import java.util.Scanner;

public class Largest2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int count=3, number, largst2;

		System.out.println("Enter 10 numbers");
		int largest = input.nextInt();
		number = input.nextInt();
		if (number > largest) {
			largst2 = largest;
			largest = number;
		} else
			largst2 = number;

		while (count <= 10) {
			number = input.nextInt();

			if (number > largest) {
				largst2 = largest;
				largest = number;
			} else if (number > largst2)
				largst2 = number;

			count++;
		}
		System.out.printf("Largest is %d and second largest is %d%n",
							largest, largst2);
	}
}
