// Ex 21.18: Token tree.

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class TokenTree {
	public static void main(String[] args){
		if (args.length < 1)
			return;
	  Tree<String> tree = new Tree<>();
	  Matcher tokens = Pattern.compile("\\w+").matcher(args[0]);
	  while (tokens.find())
			  tree.insertNode(tokens.group().toLowerCase());

	  System.out.println("Preorder");
	  tree.preorderTraversal();
	  System.out.println("\nInorder");
	  tree.inorderTraversal();
	  System.out.println("\nPostorder");
	  tree.postorderTraversal();
	  System.out.println();
	}
}
