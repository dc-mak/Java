// Ex 21.13: Postfix evaluation.

import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.Matcher;

public final class PostfixEvaluator {

	public static void main(String[] args){
		if (args.length < 1)
			return;
		PostfixEvaluator p = new PostfixEvaluator();

		System.out.println(p.evaluatePostfixExpression(args[0]));
	}

	public int evaluatePostfixExpression(String postfixExpr) {
		final Deque<Integer> stack = new LinkedList<>();

		final Matcher postfix = InfixToPostfixConverter.tokens.matcher(postfixExpr);
		
		while (postfix.find()) {
			final String token = postfix.group();
			if (InfixToPostfixConverter.isNum.test(token)) {
				stack.push(Integer.parseInt(token));
			} else /* isOperator(c) */ { 
				stack.push(calculate(stack.pop(), token, stack.pop()));
			}
		}
		return stack.pop();
	}

	public int calculate(int a, String op, int b) {
		switch (op) {
			case "^":int j = 1;
					 for (int i = 0; i < b; i++)
						  j *= a;
					  return j;
			case "*": return  b * a;
			case "/": return  b / a;
			case "%": return  b % a;
			case "-": return  b - a;
			default : return  b + a;
		}
	}
}
