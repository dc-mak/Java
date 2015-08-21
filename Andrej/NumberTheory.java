import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math.*;

public class NumberTheory {
	public static boolean isPrime(int n) {
		assert (n >= 2);
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		for (int i = 1; i < (int) Math.sqrt(n); i++)
			if (n % (6*i-1) == 0 || n % (6*i+1) == 0)
				return false;
		return true;
	}

	public static int gcd(int m, int n) {
		while (m != 0) {
			int m_ = m;
			m = n % m;
			n = m_;
		}
		return n;
	}

	// Sieve of Sundaram: generates	all ODD primes below 2*LIM + 2.
	// 2 is added manually.
	public static ArrayList<Integer> sundaram(int n) {
		final int LIM = (int) (Math.sqrt(n) / 2.0);
		final boolean[] primes = new boolean[LIM];
		Arrays.fill(primes, true);

		// Eliminate
		for (int j = 1; j < LIM; j++)
			for (int i = 1; i <= j; i++) {
				int compound = i + j + 2*i*j;
				if (compound <= LIM)
					primes[compound-1] = false;
			}

		// Collate. n / ln (n) ~ number of primes below n
		final ArrayList<Integer> result =
			new ArrayList<Integer>((int) (n / Math.log (n)));
		result.add(2);
		for (int i = 0; i < LIM; i++)
			if (primes[i])
				result.add(2*i + 3);
		return result;
	}

	public static String FTA(int n) {
		final ArrayList<Integer> primes = sundaram(n);
		String result = "";
		for (int p : primes) {
			int exp = 0;
			while (n % p == 0) {
				exp++;
				n /= p;
			}
			result += p+"^"+exp+".";
		}
		return result.substring(0, result.length()-2);
	}
}
