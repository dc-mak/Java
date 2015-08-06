// Ex 4.6: Sum integers from 1 to 10.

public class Sum {
	public static void main(String[] args) {

		int sum = 0;
		int x   = 1;

		while (x <= 10) {
			sum += x++;
			System.out.printf("%d:\t%d%n", x-1, sum);
		}
	}
}
