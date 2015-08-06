// 7.28: Race between the tortoise and the hare.

import java.util.Random;
import java.util.Arrays;

public class Race {
	public static int tort;
	public static int hare;
	public static char[] track = new char[80];
	public static final Random rand = new Random();

	public static void main(String[] args){
		tort = 0;
		hare = 0;
		Arrays.fill(track, ' ');
		System.out.println("BANG !!!!!\nAND THEY'RE OFF !!!!!!");
		while (!raceWon()) {
			showRace();
			moveTortoise();
			moveHare();
		}

		if (tort >= 69 && hare >= 69)
			System.out.println("Both win...");
		else if (tort >= 69)
			System.out.println("YAY TORTOISE WINS!");
		else
			System.out.println("Oh. Hare wins.");
	}

	public static boolean raceWon() { return tort >= 69 || hare >= 69; }

	public static void showRace() {
		Arrays.fill(track, ' ');
		String pain = "OUCH!!!";
		if (tort == hare) {
			for (int i = tort; i < pain.length(); i++)
				track[i] = pain.charAt(i-tort);
		} else {
			track[tort] = 'T';
			track[hare] = 'H';
		}
		for (char t : track)
			System.out.print(t);
	}

	public static void moveTortoise() {
		int move = 3;
		switch(rand.nextInt(10)) {
			case  5:
			case  6: move = -6; break;
			case  7:
			case  8:
			case  9: move =  1;
		}
		tort = tort + move < 0 ? 0 : tort + move;
	}

	public static void moveHare() {
		int move = 1;
		switch(rand.nextInt(10)) {
			case  0:
			case  1: move =  0; break;
			case  2:
			case  3: move =  9; break;
			case  4: move = 12; break;
			case  8:
			case  9: move =  2; break;
		}
		hare = hare + move < 0 ? 0 : hare + move;
	}
}
