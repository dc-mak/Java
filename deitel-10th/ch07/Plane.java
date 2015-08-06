// Ex 7.??: TODO

import java.util.Scanner;
import java.util.Arrays;

public class Plane {
	private static final Scanner input = new Scanner(System.in);
	private static final int CAP = 10;
	private static final boolean[] plane = new boolean[CAP];

	private static int firstPos = 0;
	private static int econPos = CAP/2;

	private static enum Class { F,E };

	public static void main(String[] args) {

		int first=0, econ=CAP/2;
		final String nextPlane = "Next flight leaves in 3 hours.";

		for (int i = 0; i < plane.length; i++) {
			Class first_pref = getPlaneClass();
			Class secnd_pref = first_pref == Class.F ? Class.E : Class.F;
			if (!addSeat(first_pref)) 
				if (getYes(secnd_pref))
					addSeat(secnd_pref);
				else {
					System.out.println(nextPlane);
					i--; // Try seat again
				}
		}
		System.out.println("Plane is full!");
	}

	private static boolean getYes(Class c) {
		String pref  = c == Class.F ? "First class" : "Economy";
		String other = c == Class.F ? "Economy" : "First class" ;
		System.out.print(pref+" is full. Would you like "+other+"? (y/n) ");

		String yn = input.next();
		while (!(yn.equals("y") || yn.equals ("n"))) {
			System.out.print("y or n only: ");
			yn = input.next();
		}
		return yn.equals("y");
	}

	private static boolean addSeat(Class c) {
		boolean added = false;
		if (c == Class.F && firstPos < CAP/2) {
				plane[firstPos] = true;
				System.out.println("You are in seat "+firstPos+".");
				firstPos++;
				added = true;
		} else if (c == Class.E && econPos < CAP) {
				plane[econPos] = true;
				System.out.println("You are in seat "+econPos+".");
				econPos++;
				added = true;
		}
		return added;
	}

	private static Class getPlaneClass() {
		return (1 == getPosInt(1, 2, "First class (1) or Economy (2)? ") ? 
			Class.F : Class.E); 
	}

	private static int getPosInt(int lower, int upper, String msg) {
		System.out.print(msg);
		int x = input.nextInt();
		while (x < lower || upper < x) {
			System.out.print("1 or 2 only: ");
			x = input.nextInt();
		}
		return x;
	}
}
