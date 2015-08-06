// Ex 4.20: Test harness for Salary.java

import java.util.Scanner;

public class SalaryTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int emps  = 3;
		int count = 1;

		while (count <= emps) {
			System.out.printf("Enter hours worked for Employee %d: ", count);
			int hours = input.nextInt();

			System.out.printf("Enter hourly rate for Employee %d: ", count);
			double rate = input.nextDouble();

			Salary emp = new Salary(hours, rate);
			System.out.printf("Employee %d gross pay is %.2f... ew.%n",
								count, emp.getGrossPay());
			count++;
		}
	}
}
