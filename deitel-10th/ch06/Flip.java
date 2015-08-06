// Ex 6.28: Grade quality bullshit.

import java.security.SecureRandom;

public class Flip {
	private enum  Side { HEADS, TAILS };
	private static final SecureRandom rand = new SecureRandom();

	public static void main(String[] args) {
		int heads=0, tails=0;
		for (int i = 0; i < 1000; i++)
			switch (flip()) {
				case HEADS : heads += 1; break;
				case TAILS : tails += 1;
			}
		System.out.println("Heads "+heads+"\tTails: "+tails+".");
	}

	private static Side flip() {
		return rand.nextInt(2) == 0 ? Side.HEADS : Side.TAILS;
	}
}
