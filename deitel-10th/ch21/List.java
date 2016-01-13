// Ex 21.9,20,21,26-27: List manipulations.

class ListNode<T> {
	T data; 
	ListNode<T> nextNode;

	ListNode(T object) { this(object, null); }

	ListNode(T object, ListNode<T> node) {
		data = object;    
		nextNode = node;  
	}

	T getData() { return data; }

	ListNode<T> getNext() { return nextNode; } 
}

public class List<T> {
	private ListNode<T> firstNode;
	private String name;

	public List() { this("list"); }

	public List(String listName) {
		name = listName;
		firstNode = null;
	}

	public void insertAt(int ind, T element) {
		if (ind < 0)
			throw new IllegalArgumentException("Index must be greater than 0.");

		if (isEmpty()) {

			if (ind != 0)
				throw new IndexOutOfBoundsException();

			insertAtFront(element);
			return;

		} else {

			if (ind == 0) {
				ListNode<T> tmp = new ListNode<>(element);
				tmp.nextNode = firstNode;
				firstNode = tmp;
				return;
			}

			ListNode<T> previous = firstNode;
			ListNode<T> current = firstNode.nextNode;
			int i;
			for (i = 1; i < ind && current != null; i++) {
				previous = current;
				current = current.nextNode;
			} 

			if (i != ind)
				throw new IndexOutOfBoundsException();

			previous.nextNode = new ListNode<>(element);
			previous.nextNode.nextNode = current;
		}
	}

	public void deleteAt(int ind) throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException();

		if (ind < 0)
			throw new IllegalArgumentException();
		
		if (ind == 0) {
			removeFromFront();
			return;
		}

		ListNode<T> previous = firstNode;
		ListNode<T> current = firstNode.nextNode;
		int i;
		for (i = 1; i < ind && current != null; i++) {
			previous = current;
			current = current.nextNode;
		}

		if (i != ind || current == null)
			throw new IndexOutOfBoundsException();

		previous.nextNode = current.nextNode;
	}
	public static <T> List<T> reversedCopy(List<T> a) {
		final List<T> reversed = new List<T>();
		while (!a.isEmpty())
			reversed.insertAtFront(a.removeFromFront());
		return reversed;
	}

	public void printReversed() {
		if (isEmpty()) {
			System.out.printf("Empty %s%n", name);
			return;
		}
																		
		ListNode<T> current = firstNode;
		final StringBuilder result = new StringBuilder();
																		
		while (current != null) {
			result.insert(0, current.data+" ");
			current = current.nextNode;
		}
																		
		System.out.printf("The %s is: %s%n", name, result.toString());
	}

	public T search(T value) { return	searchHelper(value, firstNode); }
                                                    
	public T searchHelper(T value, ListNode<T> node) { 
		if (node == null)
			return null;
		else if (node.equals(value))
			return node.data;
		else 
			return searchHelper(value, node.nextNode);
	}

	public void insertAtFront(T insertItem) {
		if (isEmpty()) 
			firstNode = new ListNode<T>(insertItem);
		else 
			firstNode = new ListNode<T>(insertItem, firstNode);
	}

	public void insertAtBack(T insertItem) {
		if (isEmpty()) {
			firstNode = new ListNode<T>(insertItem);
		} else {
			ListNode<T> current = firstNode;
			while (current.nextNode != null)
				current = current.nextNode;
			current.nextNode = new ListNode<T>(insertItem);
		}
	}

	public T removeFromFront() throws EmptyListException {
		if (isEmpty()) 
			throw new EmptyListException(name);
		T removedItem = firstNode.data;

		if (firstNode.nextNode == null)
			firstNode = null;
		else
			firstNode = firstNode.nextNode;
		return removedItem; 
	}

	public T removeFromBack() throws EmptyListException {
		if (isEmpty()) 
			throw new EmptyListException(name);
		
		if (firstNode.nextNode == null) {
			final T result = firstNode.data;
			firstNode = null;
			return result;
		}

		ListNode<T> secndLast = firstNode;
		ListNode<T> lastNode = firstNode.nextNode;

		while (lastNode.nextNode != null) {
			secndLast = lastNode;
			lastNode = lastNode.nextNode;
		}

		final T result = lastNode.data;
		secndLast.nextNode = null;
		return result; 
	}

	public boolean isEmpty() { return firstNode == null; }

	public void print() {
		if (isEmpty()) {
			System.out.printf("Empty %s%n", name);
			return;
		}
		System.out.printf("The %s is: ", name);
		ListNode<T> current = firstNode;

		while (current != null) {
			System.out.printf("%s ", current.data);
			current = current.nextNode;
		}
		System.out.println();
	} 
} 
