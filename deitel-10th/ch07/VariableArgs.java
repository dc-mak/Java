// Ex 7.14 Product using variable argument lists.

public class VariableArgs {
	public static void main(String[] args) {
		System.out.println(product(1));
		System.out.println(product(1,2));
		System.out.println(product(1,2,3));
		System.out.println(product(1,2,3,4));
	}
	public static int product(int... vals) {
		int total=1;
		for (int v : vals)
			total *= v;
		return total;
	}
}
