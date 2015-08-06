// Ex 6.34: Binary, Octal and Hex table of numbers 1 to 256.

public class BinOctHex {
	public static void main(String[] args) {
		System.out.println("Dec\tHex\tOct\tBin");
		for (int i = 1; i <= 256; i++) {
			System.out.printf("%3d\t%3s\t%3s\t%9s%n",
							  i, toHex(i), toOct(i), toBin(i));
		}
	}

	public static String toBin(int n) {
		String result = "";
		while (n != 0) {
			result = n % 2 + result;
			n /= 2;
		}
		return result;
	}
	public static String toOct(int n) {
		String result = "";
		while (n != 0) {
			result = n % 8 + result;
			n /= 8;
		}
		return result;
	}

	public static String toHex(int n) {
		String result = "";
		while (n != 0) {
			String digit = "";
			switch (n % 16) {
				case 15: digit += "F"; break;
				case 14: digit += "E"; break;
				case 13: digit += "D"; break;
				case 12: digit += "C"; break;
				case 11: digit += "B"; break;
				case 10: digit += "A"; break;
				default: digit += n % 16;
			}
			result = digit + result;
			n /= 16;
		}
		return result;
	}
}
