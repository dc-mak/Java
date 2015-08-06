// Ex 3.13: Testing Employee.java

public class EmployeeTest {
	public static void main(String[] args) {

		Employee emp1 = new Employee("Jane", "Doe", 5.00);
		Employee emp2 = new Employee("Jean", "Doe", 4.00);

		System.out.printf("Employee 1 Salary: %.2f%n", emp1.getSalary());
		System.out.printf("Employee 2 Salary: %.2f%n", emp2.getSalary());

		emp1.setSalary(emp1.getSalary() * 1.1);
		emp2.setSalary(emp2.getSalary() * 1.1);

		System.out.printf("Employee 1 Salary: %.2f%n", emp1.getSalary());
		System.out.printf("Employee 2 Salary: %.2f%n", emp2.getSalary());
	}
}
