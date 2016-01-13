// Ex 21.10: Reverse a sentence's words

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public final class Palindrome { 
	public static void main(String[] args){
		if (args.length < 1)
			return;

		final String word = args[0].replaceAll("(\\p{Punct}|\\s)", "");
		final int length = word.length();

		Stack<Character> stack = new Stack<Character>();

		// To use a stream with Characters we need String.toChars() and
		// mapToObj(i -> (char) i).
		for (char c : word.substring(0, length/2).toCharArray())
			stack.push(c);

		for (char c : (word.substring((length + 1)/2, length).toCharArray()))
			if (c != stack.pop()) {
				System.out.println(false); 
				return;
			}

		System.out.println(true);
	}
}
