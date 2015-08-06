// Ex 7.16: Sum doubles supplied

public class Sum {
	public static void main(String[] args) {
		double total = 0.0;
		for (String a : args)
			try { total += Double.parseDouble(a); }
			catch (NumberFormatException nfe) {} // Yes I know, bad...
		System.out.println(total);
	}
}
