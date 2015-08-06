// Ex 6.28: Grade quality bullshit.

import java.util.Scanner;

public class Quality {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter mark: ");
		int m = getPosInt(input);
		System.out.println(qPoints(m));
	}

	private static int getPosInt(Scanner input) {
		int x = input.nextInt();
		while (x < 0 || 100 < x) {
			System.out.print("Range 1-100 only: ");
			x = input.nextInt();
		}
		return x;
	}

	private static int qPoints(int m) {
		switch (m / 10) {
			case 10:
			case  9: return 4;
			case  8: return 3;
			case  7: return 1;
			default: return 0;
		}
	}
}
