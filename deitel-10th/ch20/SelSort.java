// Ex 20.4: Generic selection sort.

import java.util.Arrays;

public final class SelSort {
	public static <T extends Comparable<T>> void sort(T[] data) {
		for (int i = 0; i < data.length; i++) {
			int min = i;
			for (int j = i+1; j < data.length; j++)
				if (data[j].compareTo(data[min]) < 0)
					min = j;

			final T tmp = data[i];
			data[i] = data[min];
			data[min] = tmp;
		}
	}

	public static void main(String[] args){
		final Integer[] testInt = {3, 4, 6, 9, 2, 0, 4};
		final Double[] testDouble = {4.3, 6.5, 6.3, 9.2, 1.4, 3.8, 5.7};

		System.out.printf("Before sorting:%nInt: %n%s%nDouble:%n%s%n",
				Arrays.toString(testInt), Arrays.toString(testDouble));
		sort(testInt);
		sort(testDouble);
		System.out.printf("After sorting:%nInt: %n%s%nDouble:%n%s%n",
				Arrays.toString(testInt), Arrays.toString(testDouble));

	}
}
