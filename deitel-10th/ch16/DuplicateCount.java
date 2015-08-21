// Ex 16.16: Counting duplicate words in a sentence.

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DuplicateCount {
	public static void main(String[] args){
		if (args.length < 1)
			return;
		
		final Map<String,Integer> words = new HashMap<>();
		final Matcher mat = Pattern.compile("\\w+").matcher(args[0]);

		while (mat.find()) {
			String word = mat.group().toLowerCase();	
			if (words.containsKey(word))
				words.put(word, words.get(word)+1);
			else
				words.put(word, 1);
		}

		for (String s : words.keySet())
			if (words.get(s) > 1)
				System.out.println(s);
	}
}
