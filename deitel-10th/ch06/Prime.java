// Ex 6.25: Find the perfect numbers between 1 and 1000 by brute force.

public class Prime {
	public static void main(String[] args) {
		for (int i = 2; i <= 10000; i++)
			if (isPrime(i))
				System.out.println(i);
	}
	private static boolean isPrime(int x) {
		int sqrt = (int) Math.sqrt(x);
		for (int i = 2; i < sqrt; i++)
			if (x % i == 0)
				return false;
		return true;
	}
}
