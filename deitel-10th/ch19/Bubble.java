// Ex 19.5: Bubble sort.

import java.util.Random;
import java.util.Arrays;

public final class Bubble {
	public static void main(String[] args){
		int[] rand = new Random().ints(20, 0, 161).toArray();
		System.out.println("Unsorted");
		System.out.println(Arrays.toString(rand));
		sort(rand);
		System.out.println("Sorted");
		System.out.println(Arrays.toString(rand));

		// recursive bubble
		rand = new Random().ints(20, 0, 161).toArray();
		System.out.println("\nRecursive");
		System.out.println(Arrays.toString(rand));
		recSort(rand);
		System.out.println("Sorted");
		System.out.println(Arrays.toString(rand));

		// recursive insert
		rand = new Random(3).ints(20, 0, 161).toArray();
		System.out.println("\nInsertion");
		System.out.println(Arrays.toString(rand));
		recIns(rand);
		System.out.println("Sorted");
		System.out.println(Arrays.toString(rand));
	}

	private static void sort(int[] arr) {
		for (int i = arr.length - 1; i > 1; i--) {
			boolean swaps = false;
			for (int j = 1; j <= i; j++) {
				if (arr[j-1] > arr[j]) {
					swaps = true;
					final int tmp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = tmp;
				}
			}
			if (!swaps)
				break;
		}
	}
	
	private static void recSort (int[] arr) { recursive(arr, 0, true); }

	private static void recursive(int[] arr, int n, boolean s) {
		if (n >= arr.length - 1 || !s)
			return;

		boolean swaps = false;
		for (int j = 1; j <= arr.length - n - 1; j++)
			if (arr[j-1] > arr[j]) {
				swaps = true;
				final int tmp = arr[j-1];
				arr[j-1] = arr[j];
				arr[j] = tmp;
			}

		recursive(arr, n+1, swaps);
	}

	private static void recIns(int[] arr) { recursiveIns(arr, 0); }

	private static void insert(int x, int arr[], int start) {
		int i;
		for (i = start+1; i < arr.length; i++)
			if (x < arr[i])
				break;
			else
				arr[i-1] = arr[i];
		arr[i-1] = x;
	}

	private static void recursiveIns(int[] arr, int start) {
		if (start >= arr.length - 1)
			return;
		recursiveIns(arr, start+1);
		insert(arr[start], arr, start);
	}

}
