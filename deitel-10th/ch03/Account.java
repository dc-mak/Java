// Ex 3.11: Add withdraw method to Account balance. Do not allow withdrawals
// greater than balance.

public class Account {

   private String name;
   private double balance;

   public Account(String name, double balance) {
      this.name = name;

      if (balance > 0.0)
         this.balance = balance;
   }

   public void deposit(double depositAmount) {

      if (depositAmount > 0.0)
         balance = balance + depositAmount;
   }

   public double getBalance() {
      return balance;
   }

   // New method, withdraw
   public void withdraw(double amt) {
	   if (balance >= amt)
		   balance = balance - amt;
	   if (balance < amt)
		   System.out.println("Withdrawal amount exceeded account balance.");
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}
