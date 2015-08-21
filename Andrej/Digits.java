public class Digits {
	public static int number(int n) {
		assert (n >= 0);
		int digits = 0;
		do {
			digits++;
			n /= 10;
		} while (n != 0);
		return digits;
	}

	public static int sum(int n) {
		assert (n >= 0);
		int sum = 0;
		do {
			sum += n % 10;
			n /= 10;
		} while (n != 0);
		return sum;
	}

	public static String binary(int n) {
		assert (n >= 0);
		String result = "";
		do {
			result +=  n % 2;
			n /= 2;
		} while (n != 0);
		return result;
	}
}
