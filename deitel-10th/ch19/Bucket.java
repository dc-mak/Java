// Ex 19.7: Bucket sort (radix?)

import java.util.Arrays;
import java.util.Random;

public final class Bucket {
	
	public static final int LIM = 161;
	public static void main(String[] args){
		final int[] rand = new Random().ints(40, 0, LIM).toArray();
		System.out.println(Arrays.toString(rand));
		sort(rand);
		System.out.println(Arrays.toString(rand));
	}

	private static void sort(int[] arr) {
		if (arr.length < 1)
			return;

		final int reps = (int) Math.ceil(Math.log10(LIM));
		final int[][][] bucket = new int[2][10][arr.length];

		reset(bucket[0]);
		for (int j = 0; j < arr.length; j++)
			insert(arr[j], bucket[0][arr[j] % 10]);

		int i;
		for (i = 1; i < reps; i++) {
			reset(bucket[i % 2]);
			for (int j = 0; j < arr.length; j++) {
				final int n = next(bucket[1 - i % 2]);
				insert(n, bucket[i % 2][digit(n, i)]); 
			}
		}

		reset(bucket[i % 2]);
		for (int j = 0; j < arr.length; j++)
			arr[j] = next(bucket[1 - i % 2]);
	}

	private static int row;
	private static int col;

	private static int next(int[][] bucket) {
		do {
			col = col == bucket[0].length ? 0 : col+1;
			row = col == 0 ? (row == bucket.length ? 0 : row+1) : row;
			if (bucket[row][col] != -1)
				return bucket[row][col];
			else
				col = bucket[0].length;
		} while (true);
	}

	private static void reset(int[][] bucket) {
		col = bucket[0].length;
		row = bucket.length;
		for (int[] xs : bucket)
			Arrays.fill(xs, -1);
	}

	private static int digit(int n, int pos) {
		for (int i = 0; i < pos; i++)
			n /= 10;
		return n % 10;
	}

	private static int insert(int n, int[] arr) {
		int pos = 0;

		while (arr[pos] != -1 && pos < arr.length)
			pos++;

		return arr[pos] = n;
	}

		
}
