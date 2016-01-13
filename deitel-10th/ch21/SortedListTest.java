// Ex 21.7: Sorted linked list.

import java.util.Random;

public final class SortedListTest { 
	public static void main(String[] args) {
		final int LIM = 25;
		final int TOP = 101;
		final Random r = new Random();
		final SortedList<Integer> test1 = new SortedList<>();
		final SortedList<Integer> test2 = new SortedList<>();

		for (int i = 0; i < LIM; i++)
			test1.insert(r.nextInt(TOP));

		for (int i = 0; i < LIM; i++)
			test2.insert(r.nextInt(TOP));

		System.out.printf("List 1: %s%nList 2: %s%n", test1, test2);
		test1.merge(test2);
		System.out.printf("List 1: %s%nList 2: %s%n", test1, test2);
	}
}
