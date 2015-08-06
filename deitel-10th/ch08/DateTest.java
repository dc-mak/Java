// Ex 8.88: Testing Date.java

public class DateTest {
	public static void main(String[] args) {
		Date d = new Date(25, 8, 2015);

		for (int i = 0; i < 365; i++) {
			System.out.println(d);
			d.nextDay();
		}
	}
}
