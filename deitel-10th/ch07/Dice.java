// Ex 7.17: Simulate 36,000 dice rolls.

import java.util.Random;

public class Dice {
	public static void main(String[] args) {
		Random rand      = new Random(5);
		final int LIMIT  = 36000000;
		final int[] freq = new int[12];

		for (int i = 0; i < LIMIT; i++)
			freq[rand.nextInt(6)+rand.nextInt(6)]++;
		
		System.out.println("\t1\t2\t3\t4\t5\t6");
		for (int row = 0; row < 6; row++) {
			System.out.print((row+1)+"\t");
			for (int col = 0; col < 6; col++)
					System.out.printf("%d%s", freq[col+row], col==5?"\n":"\t");
		}
	}
}
