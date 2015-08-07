public class Scopes {
	public final int a;

	public Scopes(String num) {
		int b = Integer.parseInt(num);

		try {
			b = 5/b;
		} catch (ArithmeticException ae) {
			b = 0;
		}
		a = b;
	}
}
