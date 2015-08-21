// Ex 17.13: Removing letter duplicates.

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

public final class LetterDuplicate {
	public static void main(String[] args){
		final Random r = new Random();
		final List<Character> chars = new ArrayList<>(30);
		for (int i = 0; i < 30; i++)
			chars.add((char) ('a'+r.nextInt('z'-'a')));

		chars.stream()
			 .sorted()
			 .sorted(Comparator.reverseOrder())
			 .collect(Collectors.toCollection(TreeSet::new))
			 .stream()
			 .forEach(System.out::println);
	}
}
