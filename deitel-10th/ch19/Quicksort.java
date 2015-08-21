// Ex 19.10: Quicksort, based on CLRS3.

import java.util.Random;
import java.util.Arrays;

public final class Quicksort {
	public static void main(String[] args){
		final int[] rand = new Random().ints(20, 0 , 161).toArray();
		System.out.println("Unsorted");
		System.out.println(Arrays.toString(rand));
		sort(rand);
		System.out.println("Sorted");
		System.out.println(Arrays.toString(rand));
	}

	private static void sort(int[] arr) { qsort(arr, 0, arr.length-1); }
	private static void qsort(int[] arr, int start, int end) {
		if (start >= end)
			return;

		final int pivot = part(arr, start, end);
		qsort(arr, start, pivot-1);
		qsort(arr, pivot+1, end);
	}
	
	private static int part(int[] arr, int start, int end) {
		final int x = arr[end];
		int i = start-1;
		for (int j = start; j < end; j++)
			if (arr[j] <= x) {
				i++;
				final int tmp = arr[j];
				arr[j] = arr[i];
				arr[i] = tmp;
			}
		final int tmp = arr[end];
		arr[end] = arr[i+1];
		arr[i+1] = tmp;
		return i+1;
	}

}
