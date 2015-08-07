// Ex 10.12

import java.util.Random;

public class Birthday {
	public static void main(String[] args){
		Employee[] emps = {
			new CommissionEmployee("Mr. E", "Mann", "111-11-111", 5000, 0.03),
			new BasePlusCommissionEmployee("Edward", "Nigma", "222-22-222", 4000, 0.04, 300),
			new HourlyEmployee("Mrs. C", "P", "333-33-333", 103.5, 50),
			new SalariedEmployee("John", "Doe", "444-44-444", 78),
			new PieceWorker("Jane", "Doe", "555-55-555", 37.5, 40)};

		Random rand = new Random(5);

		for (Employee e : emps)
			e.setDate(new Date(1+rand.nextInt(12),
							   1+rand.nextInt(28),
							   1970+rand.nextInt(20)));

		int month = 8;

		// I'm aware this may not be exactly "readable" but still.
		for (Employee e : emps)
			System.out.println(e +
				(month == e.getDate().getMonth() ?
					 "\nBirthday bonus! :" + (e.getPaymentAmount()+100.0) :
					 "\nPayment: "+e.getPaymentAmount()) + "\n");
	}
}
