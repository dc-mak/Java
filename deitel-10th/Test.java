// Ex 8.??: TODO

import java.util.Scanner;

public class Test {
	private static final Scanner input = new Scanner(System.in);
	public static void main(String[] args) {

	}

	private static int getPosInt(int lower, int upper, String msg) {
		int x = input.nextInt();
		while (x < lower || upper < x) {
			System.out.println(msg);
			x = input.nextInt();
		}
		return x;
	}
}
