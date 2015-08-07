// Ex 10.14: Testing addition of PieceWorker class.

public class PayrollSystemTest  {
	public static void main(String[] args)  {
		Employee[] employees = {
			new SalariedEmployee("John", "Smith", "111-11-1111", 800.00),
			new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40),
			new CommissionEmployee(
					"Sue", "Jones", "333-33-3333", 10000, .06),
			new BasePlusCommissionEmployee(
					"Bob", "Lewis", "444-44-4444", 5000, .04, 300),
			new PieceWorker(
					"Drew", "Peacock", "666-66-66666", 37.7, 400)};

		for (Employee currentEmployee : employees)
			System.out.printf("%s%n%s%.2f%n%n",
					currentEmployee,
					"Earnings :", currentEmployee.getPaymentAmount());
	}
}
