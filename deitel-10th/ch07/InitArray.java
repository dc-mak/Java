// Ex 7.15: InitArray.java
// Initializing the elements of an array to default values of zero.

public class InitArray 
{
   public static void main(String[] args)
   {
	  int SIZE;	// ideally final but dump compiler
	  try { SIZE = args.length > 0 ? Integer.parseInt(args[0]) : 10; }
	  catch (NumberFormatException nfe) {SIZE = 10;}
      int[] array = new int[args.length > 0 ? Integer.parseInt(args[0]) : 10];
	  // really would like it to be final but compiler errror..

      System.out.printf("%s%8s%n", "Index", "Value"); 
      
      for (int counter = 0; counter < array.length; counter++)
         System.out.printf("%5d%8d%n", counter, array[counter]);
   } 
} 
