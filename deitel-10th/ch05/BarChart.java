//

import java.util.Scanner;

public class BarChart {

	public static void main (String[] args) {
		Scanner input = new Scanner (System.in);

		System.out.println ("Enter 5 numbers between 1 and 30 (inclusive)");
		int dig1 = getPosInt(input);
		int dig2 = getPosInt(input);
		int dig3 = getPosInt(input);
		int dig4 = getPosInt(input);
		int dig5 = getPosInt(input);

		for (int i = 1; i <= dig1; i++) {
			System.out.print ('*');
		}
		System.out.print ('\n');
		for (int i = 1; i <= dig2; i++) {
			System.out.print ('*');
		}
		System.out.print ('\n');
		for (int i = 1; i <= dig3; i++) {
			System.out.print ('*');
		}
		System.out.print ('\n');
		for (int i = 1; i <= dig4; i++) {
			System.out.print ('*');
		}
		System.out.print ('\n');
		for (int i = 1; i <= dig5; i++) {
			System.out.print ('*');
		}
		System.out.print ('\n');
	}

	private static int getPosInt (Scanner input) {
		int inVal = input.nextInt();
		while (inVal < 1 || 30 < inVal) {
			System.out.println ("A number between 1 and 30 (inclusive) please.");
			System.out.print ("Enter number: ");
			inVal = input.nextInt();
		}
		return inVal;
	}
}
