// Ex 16.20: Insert tokenised words into a TreeSet for automatic sorting.

import java.util.TreeSet;
public final class SortWords {
	public static void main(String[] args){
		if (args.length < 1)
			return;

		final TreeSet<String> words = new TreeSet<>();
		for (String s : args[0].split("\\s+|,|\\.|;"))
			words.add(s.toLowerCase());

		System.out.println(words);
	}
}
