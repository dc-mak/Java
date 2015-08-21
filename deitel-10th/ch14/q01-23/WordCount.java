// Ex 14.18 (c) - counting occurences of words in a phrase.
//		Operations contains, indexOf, set and get's (naive) pseudocode is
//		given if I was bothered enough to implement them using standarad arrays,
//		even though ArrayList was introduced in Chapter 7. Linear search is used
//		because sorting and search have not been introduced.

// I realise this is inefficient. But I miss my Map so this is the best I can do.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class WordCount {
	public static void main(String[] args){
		if (args.length < 1)
			return;

		final ArrayList<String> words = new ArrayList<>();
		final ArrayList<Integer> count = new ArrayList<>();
		// hypenated and apostrophe words, order is important
		final String token = "\\w+-\\w+|\\w+'\\w*|'\\w+|\\w+";
		Matcher mat = Pattern.compile(token).matcher(args[0]);
		while (mat.find()) {
			String s = mat.group().toLowerCase();
			int indx = words.indexOf(s);
			if (indx == -1 ) {
				words.add(s);
				indx = words.indexOf(s);
				count.add(indx, 0);
			}
			count.set(indx, count.get(indx+1));
		}

		assert (words.size() == count.size());
		for (int i = 0; i < words.size(); i++)
			System.out.println(words.get(i)+": "+count.get(i));
	}
}
/* Assume count and words are static and instance level variables.
 * Add a instance variable word_count for number of words in array.
 *
 * contains : String -> bool : 
 * \x -> for (String s : words)
 *			if (s.equals(x))
 *				return true;
 *			return false;
 * 
 * Actually, with a -1 return, you don't even need contains.
 *
 * indexOf : string -> int
 * \x -> for (int i = 0; i < words.length; i++)
 *			if (x.equals(s))
 *				return i;
 *			return -1;
 * 
 * get/set are unnecessary, with indexOf and word_count, array can be
 * directly accessed
 *
 * add : string -> unit
 * \x -> if (word_count == words.length)
 *			words = Arrays.copyOf(words, 2*word_count);
 *			count = Arrays.copyOf(count, 2*word_count);
 *		 words[word_count] = x;
 *		 word_count++;
 */
