// Ex 2.33: BMI calculator.

import java.util.Scanner;

public class BMI {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Please enter weight in kilograms: ");
		int weight = input.nextInt();

		System.out.print("Please enter height in metres: ");
		int height = input.nextInt();

		System.out.printf("Your BMI is %d%n", weight/(height*height));
		System.out.println("BMI VALUES");
		System.out.println("Underweight:\tless that 18.5");
		System.out.println("Normal:\t\tbetween 18.5 and 24.9");
		System.out.println("Overweight:\tbetween 25 and 29.9");
		System.out.println("Obese:\t\t30 or greater");
	}
}
