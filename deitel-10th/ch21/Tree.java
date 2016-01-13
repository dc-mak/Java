// Ex 21.22-25: Binary tree deletion and output.

import java.util.Queue;
import java.util.LinkedList;

class TreeNode<T extends Comparable<T>> {
	TreeNode<T> leftNode;
	T data; 
	TreeNode<T> rightNode;

	public TreeNode(T nodeData) {
		data = nodeData;
		leftNode = rightNode = null; 
	}

	public void insert(T insertValue) {
		if (insertValue.compareTo(data) < 0) {
			if (leftNode == null)
				leftNode = new TreeNode<T>(insertValue);
			else 
				leftNode.insert(insertValue);
		}
		else if (insertValue.compareTo(data) > 0) {
			if (rightNode == null)
				rightNode = new TreeNode<T>(insertValue);
			else 
				rightNode.insert(insertValue);
		}
	}
} 

public class Tree<T extends Comparable<T>> {

	private TreeNode<T> root;

	public Tree() { root = null; }

	public void insertNode(T insertValue) {
		if (root == null)
			root = new TreeNode<T>(insertValue); 
		else
			root.insert(insertValue); 
	}

	public T contains(T value) {
		TreeNode<T> current = root;
		while (current != null) {
			final int compare = value.compareTo(current.data);
			if (compare == 0)
				return current.data;
			else if (compare < 0)
				current = current.leftNode;
			else
				current = current.rightNode;
		}
		return null;
	}

	private void outputTreeHelper(TreeNode<T> current, int totalSpaces) {
		while (current != null) {
			outputTreeHelper(current.rightNode, totalSpaces + 5);
			for (int i = 0; i < totalSpaces; i++) {
				System.out.print(" ");
			}
			System.out.print(current.data+"\n");
			current = current.leftNode;
			totalSpaces += 5;
		}
	}

	public void outputTree() { outputTreeHelper(root, 0); }

	public void levelOrder() {
		final Queue<TreeNode<T>> queue = new LinkedList<>();
		if (root != null)
			queue.offer(root);
		while (queue.size() > 0) {
			final TreeNode<T> node = queue.poll();
			System.out.printf(node.data+" ");
			if (node.leftNode != null)
				queue.offer(node.leftNode);
			if (node.rightNode != null)
				queue.offer(node.rightNode);
		}
		System.out.println();
	}

	public void preorderTraversal() { preorderHelper(root); }

	private void preorderHelper(TreeNode<T> node) {
		if (node == null)
			return;
		System.out.printf("%s ", node.data); 
		preorderHelper(node.leftNode); 
		preorderHelper(node.rightNode); 
	}

	public void inorderTraversal() { inorderHelper(root); }

	private void inorderHelper(TreeNode<T> node) {
		if (node == null)
			return;
		inorderHelper(node.leftNode); 
		System.out.printf("%s ", node.data); 
		inorderHelper(node.rightNode); 
	}

	public void postorderTraversal() { postorderHelper(root); }

	private void postorderHelper(TreeNode<T> node) {
		if (node == null)
			return;
		postorderHelper(node.leftNode); 
		postorderHelper(node.rightNode); 
		System.out.printf("%s ", node.data); 
	}

	public void deleteKey(T value) {
		if (root == null) {
			System.out.println("Empty tree");
			return;
		}

		TreeNode<T> deleteParent = root;
		int compare = value.compareTo(deleteParent.data);
		final boolean rootIsVal = compare == 0;
		boolean leftChild = compare < 0;
		TreeNode<T> current = leftChild ? deleteParent.leftNode : deleteParent.rightNode;

		// find value, root or otherwise
		while (compare != 0 && current != null) {
			compare = value.compareTo(current.data);
			if (compare == 0)
				break;

			deleteParent = current;
			leftChild = compare < 0;
			current = leftChild? current.leftNode : current.rightNode;
		}

		// assert that value has been found
		if (compare != 0) {
			System.out.println("Key not found.");
			return;
		}

		// data about point
		final boolean left = leftChild;
		final TreeNode<T> parent = rootIsVal ? null : deleteParent;
		final TreeNode<T> toDelete = rootIsVal ?  deleteParent : current;

		final boolean toDeleteLeftNull = (toDelete.leftNode == null);
		final TreeNode<T> replacement;

		// node to be deleted has 0 children or 1 child
		if (toDeleteLeftNull || toDelete.rightNode == null) {
			replacement = toDeleteLeftNull ?  toDelete.rightNode : toDelete.leftNode;
		} else { // 2 children: find successor

			TreeNode<T> parentOfReplace = toDelete.leftNode;
			while (parentOfReplace.rightNode.rightNode != null)
				parentOfReplace = parentOfReplace.rightNode;
			final TreeNode<T> replaceParent = parentOfReplace;

			// successor found, release it and place in left
			replacement = replaceParent.rightNode;
			replaceParent.rightNode = replaceParent.leftNode;

			// replacement gets the children of the node that is about to be deleted
			replacement.rightNode = toDelete.rightNode;
			replacement.leftNode = toDelete.leftNode;
		}

		// careful juggling
		if (rootIsVal)
			root = replacement;
		else
			if (left)
				parent.leftNode = replacement;
			else
				parent.rightNode = replacement;
	}
}
