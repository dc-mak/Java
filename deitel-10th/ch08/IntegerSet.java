// Ex 8.13: Set of integers.

import java.util.Arrays;

public class IntegerSet {
	private static final int LIM = 101;
	private boolean[] set = new boolean[LIM];

	public static IntegerSet intersection(IntegerSet s1, IntegerSet s2) {
		IntegerSet ret_set = new IntegerSet();
		for (int i = 0; i < LIM; i++)
			if (s1.set[i] && s2.set[i])
				ret_set.set[i] = true;
		return ret_set;
	}

	private boolean check(int i) {
		if (i < 0 || LIM < i)
			throw new IllegalArgumentException(
				"Set elements range from 0 to "+(LIM-1)+" only.");
		return true;
	}

	public void insert(int i) { if (check(i)) set[i] = true;  }
	public void delete(int i) { if (check(i)) set[i] = false; }

	public String toString() {
		String result = "";
		for (int i = 0; i < LIM; i++)
			if (set[i])
				result += i+" ";
		return result.equals("") ? "---" : result;
	}

	public boolean isEqual(IntegerSet s) {
		for (int i = 0; i < LIM; i++)
			if (set[i] != s.set[i])
				return false;
		return true;
	}

	public IntegerSet() { Arrays.fill(set, false); }
}
