// Ex 8.15: Rational numbers.

public class Rational {
	private int num;
	private int den;

	public Rational(int num, int den) {
		if (den == 0)
			throw new IllegalArgumentException(
					"Denominator cannot be 0.");
		int n = gcd(num,den);
		this.num = num / n * Integer.signum(den);
		this.den = den / n * Integer.signum(den);
	}

	public Rational plus(Rational b) {
		num = num * b.den + b.num * den;
		den = den * b.den;
		norm();
		return this;
	}

	public Rational minus(Rational b) {
		num = num * b.den - b.num * den;
		den = den * b.den;
		norm();
		return this;
	}

	public Rational times(Rational b) {
		num *= b.num;
		den *= b.den;
		norm();
		return this;
	}

	public Rational divide(Rational b) {
		num *= b.den;
		den *= b.num;
		norm();
		return this;
	}
	
	private void norm() {
		int n = gcd(Math.abs(num), Math.abs(den));
		num /= n * Integer.signum(den);
		den /= n * Integer.signum(den);
	}
	
	private static int gcd(int m, int n) {
		while (m != 0) {
			int m_ = m;
			m = n % m;
			n = m_;
		}
		return n;
	}

	public String toString() { return num+"/"+den; }

	public String toFloatString() { return toFloatString(0); }

	public String toFloatString(int i) {
		if (i == 0)
			return String.format("%f", (double) num / den);
		else
			return String.format("%."+i+"f", (double) num / den);
	}
}
