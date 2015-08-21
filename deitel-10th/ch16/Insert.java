// 16.17: Inserting elements into a LinkedList in order.

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public final class Insert {
	public static void main(String[] args){
		final Random r = new Random();
		final List<Integer> sorted = new LinkedList<>();
		for (int i = 0; i < 24; i++) {
			final ListIterator<Integer> list = sorted.listIterator();
			final int x = r.nextInt(101);
			while (true)
				if (!list.hasNext()) {
					list.add(x);
					break;
				} else if (list.next() > x) {
					list.previous();
					list.add(x);
					break;
				}
		}
		System.out.println(sorted);
	}
}
