// Ex 21.22-25: Binary tree deletion and ouput

import java.security.SecureRandom;

public class TreeTest
{
	public static void main(String[] args) {

		Tree<Integer> tree = new Tree<Integer>();
		SecureRandom randomNumber = new SecureRandom();

		System.out.println("Inserting the following values: ");

		final int[] valArr = {0, 79, 35, 45, 5, 78, 1, 62, 9, 79, 81, 2, 94, 97};

		for (int value : valArr) {
			System.out.printf("%d ", value);
			tree.insertNode(value);
		}
		System.out.printf("%nInorder traversal%n");
		tree.inorderTraversal();
		System.out.println("\n");

		for (int i = valArr.length-1 ; i >= 0; i--) {
			System.out.println("search: "+valArr[i]+"? "+tree.contains(valArr[i]));
		}

		System.out.println();
		System.out.println("Level order traversal: ");
		tree.levelOrder();

		System.out.println();
		System.out.println("Output tree: ");
		tree.outputTree();

		for (int i = valArr.length-1 ; i >= 0; i--) {
			System.out.println("\ndelete: "+valArr[i]+"? ");
			tree.deleteKey(valArr[i]);
			tree.inorderTraversal();
		}
		System.out.println();
	}
} 
