// Fig. 9.10: CommissionEmployee.java
// CommissionEmployee class uses methods to manipulate its 
// private instance variables.
public class CommissionEmployee extends Employee
{
   private double grossSales; // gross weekly sales       
   private double commissionRate; // commission percentage

   // five-argument constructor
   public CommissionEmployee(String firstName, String lastName, 
      String socialSecurityNumber, double grossSales, 
      double commissionRate)
   {
	   super(firstName, lastName, socialSecurityNumber);

      // if grossSales is invalid throw exception
      if (grossSales < 0.0) 
         throw new IllegalArgumentException(
            "Gross sales must be >= 0.0");

      // if commissionRate is invalid throw exception
      if (commissionRate <= 0.0 || commissionRate >= 1.0)
         throw new IllegalArgumentException(
            "Commission rate must be > 0.0 and < 1.0");

      this.grossSales = grossSales;
      this.commissionRate = commissionRate;
   } // end constructor 

   // set gross sales amount
   public void setGrossSales(double grossSales)
   {
      if (grossSales < 0.0) 
         throw new IllegalArgumentException(
            "Gross sales must be >= 0.0");

      this.grossSales = grossSales;
   } 

   // return gross sales amount
   public double getGrossSales()
   {
      return grossSales;
   } 

   // set commission rate
   public void setCommissionRate(double commissionRate)
   {
      if (commissionRate <= 0.0 || commissionRate >= 1.0)
         throw new IllegalArgumentException(
            "Commission rate must be > 0.0 and < 1.0");

      this.commissionRate = commissionRate;
   } 

   // return commission rate
   public double getCommissionRate()
   {
      return commissionRate;
   }

   // calculate earnings
   public double earnings()
   {
      return getCommissionRate() * getGrossSales();
   } 

   // return String representation of CommissionEmployee object
   @Override 
   public String toString()
   {
      return String.format("%s %s%s: %.2f%n%s: %.2f", "commission", 
		super.toString(), "gross sales", getGrossSales(), 
		   "commission rate", getCommissionRate());
   } 
} // end class CommissionEmployee


/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
