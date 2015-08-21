// Ex 14.3: Comparing two string input by the user.

import java.util.Scanner;
public final class Compare {
	public static void main(String[] args){
		 Scanner input = new Scanner(System.in);
		 System.out.print("Enter first string: ");
		 String s1 = input.next();
		 System.out.print("Enter second string: ");
		 String s2 = input.next();
		 final int comp = s1.compareTo(s2);
		 if (comp < 0)
			 System.out.println("First is less than second.");
		 else if (comp > 0)
			 System.out.println("First is greater than second.");
		 else
			 System.out.println("First is equal to second.");
	}
}
