// Ex 8.6: Savings account stuff.

public class Savings {
	private static double annualInterestRate;
	private double balance;

	public void monthlyInterest() {
		balance += annualInterestRate * balance / 12;
	}
	
	public Savings(double balance) { this.balance = balance; }

	private static void modifyInterestRate(double rate) {
		annualInterestRate = rate;
	}
}
