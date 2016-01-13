// Ex 21.12: The Shunting Yard Algorithm I believe. I'll just use a regular
//			  Java API Deque for stacks instead.

import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.function.Predicate;
import java.util.LinkedList;

public final class InfixToPostfixConverter {
	public static void main(String[] args){
		if (args.length < 1)
			return;

		InfixToPostfixConverter i = new InfixToPostfixConverter();
		System.out.println(i.convertToPostfix(args[0]));
	}

	public static final String opRegex = "\\(\\)\\+\\-\\*/%\\^";
	public static final String numRegex = "\\d+";

	public static final Predicate<String> isOp  = Pattern.compile("["+opRegex+"]").asPredicate();
	public static final Predicate<String> isNum = Pattern.compile(numRegex).asPredicate();

	public static final Pattern tokens = Pattern.compile(numRegex+"|["+opRegex+"]");

	public String convertToPostfix(String infixExpr) {

		// scrub input
		final Matcher infix = tokens.matcher(infixExpr+")");
		final StringBuffer postfix = new StringBuffer();
		final Deque<String> stack = new LinkedList<>();
		stack.push("(");

		while (infix.find() && stack.size() != 0) {
			final String token = infix.group();

			if (isNum.test(token)) {

				postfix.append(token+" ");
				
			} else if (token.equals("(")) {

				stack.push(token);

			} else if (isOp.test(token)) {

				while (isOp.test(stack.peek()) && precedence(token, stack.peek()))
					postfix.append(stack.pop()+" ");

				stack.push(token);

			} else if (token.equals(")")) {

				while (isOp.test(stack.peek()) && !stack.peek().equals("("))
					postfix.append(stack.pop()+" ");

				stack.pop();
			}
		}

		return postfix.toString().trim();
	}

	private static boolean precedence(String op1, String op2) {
		return precedenceLevel(op1) < precedenceLevel(op2); 
	}

	private static int precedenceLevel(String op) {
		assert (isOp.test(op));
		switch (op) {
			case "^": return 2;
			case "*":
			case "/":
			case "%": return 1;
			case "+":
			case "-": return 0;
		}

		return -1;
	}
}
