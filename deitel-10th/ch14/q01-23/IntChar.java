// Ex 14.15: Integer value to character.

import java.util.Scanner;

public final class IntChar {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter an natural number: ");
		final int inVal = input.nextInt();
		System.out.println("Character is -- "+((char) inVal)+" -- there.");
		for (int i = 0; i < 256; i++)
			System.out.println("Character is -- "+((char) i)+" -- there.");
	}
}
