// BasePlusCommissionEmployeeTest.java
// Testing class BasePlusCommissionEmployee.

public class BasePlusCommissionEmployeeTest
{
   public static void main(String[] args) 
   {
      // instantiate BasePlusCommissionEmployee object
      BasePlusCommissionEmployee employee = 
         new BasePlusCommissionEmployee(
            "Bob", "Lewis", "333-33-3333", 5000, .04, 300);
      
      // get base-salaried commission employee data
      System.out.println(
         "Employee information obtained by get methods:");
      System.out.printf("%n%s %s%n", "First name is",
         employee.getFirstName());
      System.out.printf("%s %s%n", "Last name is", 
         employee.getLastName());
      System.out.printf("%s %s%n", "Social security number is", 
         employee.getSocialSecurityNumber());
      System.out.printf("%s %.2f%n", "Gross sales is", 
         employee.getGrossSales());
      System.out.printf("%s %.2f%n", "Commission rate is",
         employee.getCommissionRate());
      System.out.printf("%s %.2f%n", "Base salary is",
         employee.getBaseSalary());

      employee.setBaseSalary(1000); 
      
      System.out.printf("%n%s:%n%n%s%n", 
         "Updated employee information obtained by toString", 
         employee.toString());
	
	  System.out.println();
	  HourlyEmployee he = new HourlyEmployee(
		  "Drew", "Peacock", "555-55-5555", 80.0, 100.0);
	  System.out.println(he);
   } // end main
} // end class BasePlusCommissionEmployeeTest


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
