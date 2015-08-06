// Ex 4.21: Largest of 10 numbers.
import java.util.Scanner;

public class Largest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int count=2, number;

		System.out.println("Enter 10 numbers");
		int largest = input.nextInt();
		while (count <= 10) {
			number = input.nextInt();
			if (number > largest)
				largest = number;
			count++;
		}
		System.out.printf("Largest is %d%n", largest);
	}
}
