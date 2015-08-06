// Ex 6.24: Find the perfect numbers between 1 and 1000 by brute force.

public class Perfect {
	public static void main(String[] args) {
		for (int i = 1; i <= 10000; i++)
			if (isPerfect(i))
				System.out.println("Hence "+i+" is perfect.");
	}
	private static boolean isPerfect(int x) {
		int total = 1;
		int sqrt = (int) Math.sqrt(x);
		String factors = "Factors: 1 ";
		for (int i = 2; i < sqrt; i++)
			if (x % i == 0) {
				total += i + x/i;
				if (i == x/i)
					factors += i+" ";
				else
					factors += i+" "+x/i+" ";
			}

		boolean perfect = total == x;
		if (perfect)
			System.out.println(factors);
		return perfect;
	}
}
