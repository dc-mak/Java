// Ex 21.7: A sorted linked list.

public class SortedList<T extends Comparable<T>> {
	private SortedListNode<T> first;
	private SortedListNode<T> last;

	public boolean isEmpty() { return first == null; }

	@Override
	public String toString() {
		if (isEmpty())
			return "[]";

		final StringBuilder result =
			new StringBuilder("["+first.data.toString());
		SortedListNode<T> current = first;
		while (current != last) {
			current = current.next;
			result.append(", "+current.data.toString());
		}
		result.append("]");
		return result.toString();
	}
	
	// This is the worst. This is what happens if you follow engine-carriage
	// linked-list model instead of engine-link-to-engine-with-one-fewer:
	// you lose (a) recursion and (b) simple case analysis.
	public void insert(T item) {
		if (isEmpty()) { // 0 elements
			first = last = new SortedListNode<T>(item);
			return;
		}
		// 1+ elements
		SortedListNode<T> ins = new SortedListNode<T>(item);
		if (item.compareTo(first.data) < 0) {
			ins.next = first; 
			first = ins;
		} else {
			SortedListNode<T> prev = first;
			SortedListNode<T> curr = first.next;
			while (true) {
				if (prev == last) {
					prev.next = last = ins;
					break;
				} else if (item.compareTo(curr.data) < 0) {
					prev.next = ins;
					ins.next = curr;
					break;
				}
				prev = curr;
				curr = curr.next;
			}
		}
	}

	public T removeFromFront() {
	/*	if (isEmpty(())
	 *		throw new EmptySortedListException(); */

		final T result = first.data;
		first = first.next;

		return result;
	}

	public void merge(SortedList<T> a) {
		while (!a.isEmpty())
			insert(a.removeFromFront());
	}
}

class SortedListNode<T extends Comparable<T>> {
	T data;
	SortedListNode<T> next;

	@Override
	public String toString() { return data.toString(); }

	SortedListNode(T item) { this(item, null); }

	SortedListNode(T item, SortedListNode<T> node) {
		data = item;
		next = node;
	}

	T getData() { return data; }
	SortedListNode<T> getNext() { return next; }
}
