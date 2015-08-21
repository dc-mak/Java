// 14.5: Random sentences.

import java.util.Random;

public final class RandSentences {
	private static final String[] article = {"the", "a", "one", "some", "any"};
	private static final String[] noun = {"boy", "girl", "dog", "town", "car"};
	private static final String[] verb = {"drove", "jumped", "ran", "walked", "skipped"};
	private static final String[] preposition = {"to", "from", "over", "under", "on"};

	public static void main(String[] args){
		final Random rand = new Random();
		for (int i = 0; i < 20; i++)
			System.out.println(
				firstUpper(article[rand.nextInt(article.length)]) + " " +
				noun[rand.nextInt(noun.length)] + " " +
				verb[rand.nextInt(verb.length)] + " " +
				preposition[rand.nextInt(preposition.length)] + " " +
				article[rand.nextInt(article.length)] + " " +
				noun[rand.nextInt(noun.length)] + ".");

		// System.out.println(firstUpper(""));
		// System.out.println(firstUpper("a"));
	}

	// Ideally I would use regex and matched pattern references in the replacement.
	private static String firstUpper(String s) {
		if (!s.isEmpty())
			return Character.toUpperCase(s.charAt(0))+s.substring(1);
		return s;
	}
}
