// Ex 3.17: Testing HealthProfile.java

import java.util.Scanner;

public class HealthProfileTest {
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.print("Enter first name: ");
		String firstName = input.nextLine();

		System.out.print("Enter last name: ");
		String lastName = input.nextLine();

		System.out.print("Enter gender: ");
		String gender = input.nextLine();

		System.out.print("Enter day of birth: ");
		int day = input.nextInt();

		System.out.print("Enter month of birth: ");
		int month = input.nextInt();

		System.out.print("Enter year of birth: ");
		int year = input.nextInt();

		System.out.print("Enter your height(in inches): ");
		int height = input.nextInt();

		System.out.print("Enter your weight(in pounds): ");
		int weight = input.nextInt();

		HealthProfile person =
			new HealthProfile(firstName, lastName, gender,
							   day, month, year, height, weight);

		System.out.printf("First name:\t\t%s%n", person.getFirstName());
		System.out.printf("Last name:\t\t%s%n", person.getLastName());
		System.out.printf("Gender:\t\t\t%s%n", person.getGender());
		System.out.printf("Date of Birth:\t\t%d/%d/%d%n", person.getDay(), person.getMonth(), person.getYear());

		System.out.printf("Age:\t\t\t%d%n", person.getAge());
		System.out.printf("Height:\t\t\t%d%n", person.getHeight());
		System.out.printf("Weight:\t\t\t%d%n", person.getWeight());

		System.out.printf("Max Heart Rate:\t\t%d bpm%n", person.getMaxHeartRate());
		System.out.printf("Target Heart Rate:\t%.2f-%.2f bpm%n", person.getTargetLowerRate(), person.getTargetHigherRate());

		System.out.printf("Your BMI:\t\t%.2f%n", person.getBMI());
		System.out.println("BMI VALUES");
		System.out.println("Underweight:\tless that 18.5");
		System.out.println("Normal:\t\tbetween 18.5 and 24.9");
		System.out.println("Overweight:\tbetween 25 and 29.9");
		System.out.println("Obese:\t\t30 or greater");
	}
}

