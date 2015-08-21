// Ex 14.18 (a) - Counting the letters in a phrase.

public final class AlphaCount {
	public static void main(String[] args){
		if (args.length < 0)
			return;

		final char[] chars = args[0].toLowerCase().toCharArray();
		final int[]  count = new int[26];
		for (char c : chars)
			if (c - 'a' >= 0 && c - 'a' < 26)
				count[c-'a']++;

		for (int i = 0; i < 26; i++)
			System.out.println(((char)(i+'a'))+": "+count[i]);
	}
}
