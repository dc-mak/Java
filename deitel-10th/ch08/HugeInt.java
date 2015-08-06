// Ex 8.16: HUGE INTEGER!

public class HugeInt {
	private static final int SIZE = 40;
	private boolean positive = true;
	private int[] num = new int[SIZE];

	public String toString() {
		String result = "";
		
		return result;
	}

	public HugeInt add(HugeInt n) {

		return this;
	}

	public HugeInt subtract(HugeInt n) {

		return this;
	}

	public static HugeInt parse(String s) {

	}
	

	public boolean isEqualTo(HugeInt n) {

	}

	public boolean isNotEqualTo(HugeInt n) {
		return !isEqualTo(n);
	}

	public boolean isGreaterThan(HugeInt n) {

	}

	public boolean isLessThan(HugeIntn) {

	}

	public boolean isGreaterThanOrEqualTo(HugeInt n) {
		return !isGreaterThan(n);
	}

	public boolean isLessThanOrEqualTo(HugeInt n) {
		return !isLessThan(n);
	}

	public boolean isZero() {
		for (int i = 0; i < num.length; i++)
			if (num[i] != 0)
				return false;
		return true;
	}

	public HugeInt multiply(HugeInt n) {

		return this;
	}

	public HugeInt divide(HugeInt n) {

		return this;
	}
}
