// Ex 16.18: Copying and reversing linked lists.

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

public final class ReverseCopy {
	public static void main(String[] args){
		final Integer[] test = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		final List<Integer> first = new LinkedList<>(Arrays.asList(test));
		final LinkedList<Integer> secnd = new LinkedList<>();
		for (Integer i : first)
			secnd.addFirst(i);
			// secnd.add(0,i) for List
		System.out.println(secnd);
	}
}
