// Ex 6.30/31: Guess the number.

import java.util.Scanner;
import java.security.SecureRandom;

public class Guess {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		SecureRandom rand = new SecureRandom();
		String playAgain = "y";

		while (playAgain.equals("y") || playAgain.equals("Y")) {
			final int answer = 1+rand.nextInt(1000);
			int tries = 1;
			System.out.print("Enter first guess: ");
			int guess = input.nextInt();

			while (guess != answer) {
				if (guess > answer)
					System.out.println("Lower!");
				else
					System.out.println("Higher!");
				guess = input.nextInt();
			}

			switch ((tries - 1)/ 10) {
				case 0: System.out.println("Aha! You're a dick."); break;
				default: System.out.println("You can do better!");
			}
			System.out.println("Play again? (y/N)");
			playAgain = input.next();
		}
	}
}
