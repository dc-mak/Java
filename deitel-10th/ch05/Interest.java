// Ex 5.14: Interest.java with 5, 6, 7, 8, 9, and 10 %.

public class Interest {
   public static void main(String args[]) {
      double amount;
      double principal = 1000.0;
	  double percent = 0.01;

      System.out.printf("%s%20s%n", "Year", "Amount on deposit");
	  for (int i = 5; i <= 10; i++) {
		  System.out.printf("%d%%%n", i);
		  double rate = (double) i * percent;
		  for (int year = 1; year <= 10; year++) {
			 amount = principal * Math.pow(1.0 + rate, year);
			 System.out.printf("%4d%,20.2f%n", year, amount);
		  }
      }
   }
}
