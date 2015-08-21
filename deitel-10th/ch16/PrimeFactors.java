// Ex 16.19: Ok so I re-used a problem I solved before. I don't see how Sets
//			 specifically are needed here as opposed to an ArrayList but yeah.

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Arrays;
import java.lang.Math.*;

public final class PrimeFactors {
	// Sieve of Sundaram: generates	all ODD primes below 2*LIM + 2.
	// 2 is added manually.
	public static Set<Integer> sundaram(int n) {
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
		final Set<Integer> result = new HashSet<>((int) (n / Math.log (n)));
		result.add(2);
		for (int i = 0; i < LIM; i++)
			if (primes[i])
				result.add(2*i + 3);
		return result;
	}

	public static void main(String[] args) {
		if (args.length < 1)
			return;

		final int n = Integer.parseInt(args[0]);
		if (n < 2)
			return;

		final Set<Integer> primes = sundaram(n);
		final Iterator<Integer> iter = primes.iterator();

		while (iter.hasNext())
			if (n % iter.next() != 0)
				iter.remove();

		if (primes.size() == 0)
				System.out.println("Number is prime");
		else 
			System.out.println(primes);
	}
}
