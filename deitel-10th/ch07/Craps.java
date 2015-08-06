// Ex 7.18: Modified Fig 6.8.

import java.security.SecureRandom;

public class Craps {

	private static final SecureRandom rand = new SecureRandom();
	private static int all_rolls=0;

	private static int rollDice() {
		all_rolls++;
		return 2 + rand.nextInt(6) + rand.nextInt(6);
	}

	public static void main(String[] args) {

		final int GAMES  = 1000000;
		final int ROLLS  = 20;
		final int[] won  = new int[ROLLS+1];
		final int[] lost = new int[ROLLS+1];

		// Game
		for (int i = 0; i < GAMES; i++) {
			int sum = rollDice();

			if (sum == 7 || sum == 11) {
				won[0]++;
			} else if (sum == 2 || sum == 3 || sum == 12) {
				lost[0]++;
			} else {
				int point = sum;
				for (int roll = 1; true; roll++) {
					sum = rollDice();
					if (sum == point) {
						won[Math.min(roll, ROLLS)]++;
						break;
					} else if (sum == 7) {
						lost[Math.min(roll, ROLLS)]++;
						break;
					}
				}
			} 
		}

		// Games won and lost:
		System.out.printf("%s\t%-8s\t%-8s\t%-8s%n",
							"Roll", "Won", "Lost", "Ratio (Wins:Losses)");

		for (int i = 0; i < won.length-1; i++)
			System.out.printf("%-3d\t%-,8d\t%-,8d\t%.2f%n",
							i+1, won[i], lost[i], (double) won[i] / lost[i]);
		System.out.printf("20+\t%-,8d\t%-,8d\t%.2f%n",
				won[ROLLS], lost[ROLLS], (double) won[ROLLS] / lost[ROLLS]);

		// Blank line space.
		System.out.println();

		//Chances of winning:
		int wins = 0;
		for (int w : won)
			wins += w;
		System.out.printf("Chances of winning: %.3f%n", (double) wins / GAMES);

		// Blank line space.
		System.out.println();

		// Average game length
		System.out.printf("Average game length: %.1f rolls.%n",
										(double) all_rolls / GAMES);
	}
} 
