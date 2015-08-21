// Ex 17.12: Removing duplicate words.

import java.util.stream.Collectors;
import java.util.regex.Pattern;

public final class WordDuplicate {
	public static void main(String[] args){
		if (args.length < 1)
			return;

		final Pattern word = Pattern.compile("\\s+");
		word.splitAsStream(args[0])
			.map(String::toLowerCase)
			.collect(Collectors.toSet())
			.stream()
			.sorted(String.CASE_INSENSITIVE_ORDER)
			.forEach(System.out::println);
	}
}
