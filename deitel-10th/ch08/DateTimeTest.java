// Ex 8.13: Testing DateTime.java

public class DateTimeTest {
	public static void main(String[] args) {
		DateTime d1 = new DateTime(23, 56, 59, 24, 8, 2015);
		for (int i = 0; i < 362; i++) {
			d1.tick();
			System.out.println(d1);
		}

		DateTime d2 = new DateTime(22, 34, 24, 28, 2, 2016);
		for (int i = 0; i < 362; i++) {
			d.tickMinute();
			System.out.println(d2);
		}

		DateTime d3 = new DateTime(2, 50, 49, 31, 12, 2015);
		for (int i = 0; i < 24; i++) {
			d3.tickHour();
			System.out.println(d3);
		}
	} 
}
