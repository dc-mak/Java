// Ex 21.10: Reverse a sentence's words

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public final class Sentence { 
	public static void main(String[] args){
		if (args.length < 1)
			return;

		Stack<String> stack =
			Arrays.asList(args[0].split("\\s+"))
				  .stream()
				  .collect(Collectors.toCollection(Stack::new));

		while (!stack.empty())
			System.out.printf("%s ", stack.pop());

		System.out.println();
	}
}
