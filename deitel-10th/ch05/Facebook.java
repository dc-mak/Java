// Ex 5.33: Facebook user growth. I did it. I used a boolean.

public class Facebook {
	public static void main(String[] args) {
		double total = 1000000000.0;
		double rate  = 1.04;

		int month;
		boolean notHit = true;
		for (month = 0; total < 2000000000.0; month++) {
			if (total > 1500000000.0 && notHit) {
				System.out.printf("1.5 billion users hit in month %d.%n", month);
				notHit = false;
			}
			total *= rate;
		}
		System.out.printf("2 billion users hit in month %d.%n", month);
	}
}
