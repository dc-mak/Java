public class Arrays {
	public static void sum1(int[] a, int[] b) {
		assert (a.length == b.length);
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a.length; j++)
				b[i] += j == i ? 0 : a[i];
	}

	public static void sum2(int[] a, int[] b) {
		assert (a.length == b.length);
		int total = 0;
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < a.length; j++)
				total += b[i];

		total /= (a.length-1);

		for (int i = 0; i < a.length; i++)
			a[i] = total - b[i];
	}

	public static void heapify(int[] a) {
		int heapsize = a.length;
		for (int i = a.length / 2; i >= 0; i--) {
			int j = i;
			while (true) {
				final int left = 2*j + 1;
				final int right = left + 1;
				int max = 0;

				if (left <= heapsize && a[left] > a[right])
					max = left;
				else
					max = j;

				if (right <= heapsize && a[right] > a[max])
					max = right;

				if (max == j)
					break;
			//  else
					int tmp = a[j];
					a[j] = a[max];
					a[max] = tmp;
					j = max;
			}
		}
	}

	public static int stream(int[] a, long k) {
		assert (k >= 0);
		long len = a.length;
		k %= 2*len;
		if (k < len)
			return a[(int) k];
		else // k >= len
			return 1 - a[(int) (2*len-1 - k)];
	}

	public static boolean  isSubseq(int[] a, int[] b) {
		if (a.length > b.length)
			return false;

		int current = 0;
		for (int i = 0; i < b.length ; i++)
			if (b[i] == a[current])
				current++;

		return current >= a.length;
	}

	public static boolean isConsecSubseq(int[] a, int[] b) {
		if (a.length > b.length)
			return false;

		boolean match = false;
		for (int i = 0; i < b.length - a.length; i++) {
			match = true;
			for (int j = 0; j < a.length; j++)
				if (b[j+i] != a[j]) {
					match = false;
					break;
				}

			if (match) 
				return true;
		}
		return false;
	}

	// Princeton Knuth-Morris-Pratt Algorithm: youtu.be/iZ93Unvxwtw
	public static boolean KMP(int[] a, int[] b) {
		if (a.length > b.length)
			return false;

		final int RADIX = 10;
		final int[][] dfa = new int[RADIX][a.length];
		// assume all values are single digits
		dfa[a[0]][0] = 1;
		for (int x = 0, j = 1; j < a.length; j++) {
			for (int c = 0; c < RADIX; c++)
				dfa[c][j] = dfa[c][x];

			dfa[a[j]][j] = j+1;
			x = dfa[a[j]][x];
		}

		int i, j;
		for (j = 0, i = 0; i < b.length && j < a.length; i++)
			j = dfa[b[i]][j];

		return j == a.length;
	}
}
