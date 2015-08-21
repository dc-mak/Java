// Ex 10.14/15
import java.awt.
public class PayableInterfaceTest {
	public static void main(String[] args) {
		Payable[] payableObjects = {
			new Invoice("01234", "seat", 2, 375.00),
			new HourlyEmployee("Mrs. C", "P", "111-11-11111", 76.0, 60.5),
			new BasePlusCommissionEmployee("Mr. E", "Mann", "222-22-22222", 67.0, 0.09, 503.3),
			new CommissionEmployee("Mr. E", "Nigma", "333-33-33333", 560.8, 0.07),
			new SalariedEmployee("Lisa", "Barnes", "888-88-8888", 1200.00),
			new PieceWorkerEmployee("Drew", "Peacock", "666-66-66666", 10.8, 69)};

		for (Payable currentPayable : payableObjects) {
			if (currentPayable instanceof BasePlusCommissionEmployee) {

				BasePlusCommissionEmployee payable =
					(BasePlusCommissionEmployee) currentPayable;
				payable.setBaseSalary(1.10 * payable.getBaseSalary());
				System.out.printf(
						"%nnew base salary with 10%% increase is: $%,.2f",
						payable.getBaseSalary());
			}

			System.out.printf("%n%s %n%s: $%,.2f%n",
					currentPayable.toString(),
					"payment due", currentPayable.getPaymentAmount());
		}
	}
}


