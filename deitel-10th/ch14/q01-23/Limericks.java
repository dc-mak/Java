// 14.6: Limericks. Don't really care about the polish required for good limericks.

import java.util.Random;

public final class Limericks {
	private static final String[] article = {"the", "a", "one", "some", "any"};
	private static final String[] noun = {"boy", "girl", "dog", "town", "car"};
	private static final String[] verb = {"drove", "jumped", "ran", "walked", "skipped"};
	private static final String[] preposition = {"to", "from", "over", "under", "on"};
	private static final Random rand = new Random();

	private static final String[][] rhymes = 
		{{"car", "bar", "star", "jar", "scar", "tar", "cigar", "guitar"},
		{"boat", "goat", "coat", "moat", "throat", "remote", "note", "quote"}};

	public static void main(String[] args){
		for (int i = 0; i < 20; i++) {
			final int lines125 = rand.nextInt(2);
			for (int j = 0; j < 5; j++)
				System.out.println(
					firstPart() + rhymePart(lines125, j) + (j<4 ? "," : "."));
			System.out.println();
			}

	}

	private static final String rhymePart(int rhymeGroup125, int line) {
		switch (line) {
			case  0:
			case  1:
			case  4: return rhymes[rhymeGroup125][rand.nextInt(rhymes[rhymeGroup125].length)];
			default: return rhymes[1-rhymeGroup125][rand.nextInt(rhymes[1-rhymeGroup125].length)];
		}
	}

	private static final String firstPart() {
		return firstUpper(article[rand.nextInt(article.length)]) + " " +
			   noun[rand.nextInt(noun.length)] + " " +
			   verb[rand.nextInt(verb.length)] + " " +
			   preposition[rand.nextInt(preposition.length)] + " " +
			   article[rand.nextInt(article.length)] + " ";
	}

	// Ideally I would use regex and matched pattern references in the replacement.
	private static String firstUpper(String s) {
		if (!s.isEmpty())
			return Character.toUpperCase(s.charAt(0))+s.substring(1);
		return s;
	}
}
