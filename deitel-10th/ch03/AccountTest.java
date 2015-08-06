// Ex 3.11: Test withdraw method in Account.
import java.util.Scanner;

public class AccountTest {

   public static void main(String[] args) {

      Account account1 = new Account("Jane Green", 50.00);
      Account account2 = new Account("John Blue", -7.53);

      // display initial balance of each object
      System.out.printf("%s balance: $%.2f%n",
         account1.getName(), account1.getBalance());
      System.out.printf("%s balance: $%.2f%n%n",
         account2.getName(), account2.getBalance());

      // create a Scanner to obtain input from the command window
      Scanner input = new Scanner(System.in);

	  // 1 - Deposit
      System.out.print("Enter deposit amount for account1: ");
      double depositAmount = input.nextDouble();
      System.out.printf("%nadding %.2f to account1 balance%n%n",
         depositAmount);
      account1.deposit(depositAmount);

      // View
	  displayAccount(account1);
	  displayAccount(account2);

	  // 1 - Withdraw
      System.out.print("Enter withdrawal amount for account1: ");
      double withdrawalAmount = input.nextDouble();
      System.out.printf("%nwithdrawing %.2f from account1 balance%n%n",
         withdrawalAmount);
      account1.withdraw(withdrawalAmount);

      // View
	  displayAccount(account1);
	  displayAccount(account2);

	  // 2 - Deposit
      System.out.print("Enter deposit amount for account2: ");
      depositAmount = input.nextDouble();
      System.out.printf("%nadding %.2f to account2 balance%n%n",
         depositAmount);
      account2.deposit(depositAmount);

      // View
	  displayAccount(account1);
	  displayAccount(account2);

	  // 2 - Withdraw
      System.out.print("Enter withdrawal amount for account2: ");
      withdrawalAmount = input.nextDouble();
      System.out.printf("%nwithdrawing %.2f from account2 balance%n%n",
         withdrawalAmount);
      account2.withdraw(withdrawalAmount);

      // View
	  displayAccount(account1);
	  displayAccount(account2);

   }

   public static void displayAccount(Account acc) {
      System.out.printf("%s balance: $%.2f%n",
         acc.getName(), acc.getBalance());
   }
}
