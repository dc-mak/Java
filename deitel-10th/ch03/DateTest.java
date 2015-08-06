// Ex 3.14: Test Date.java

public class DateTest {
	public static void main(String[] args) {
		Date date = new Date(27, 07, 2015);

		date.setDay(8);
		System.out.printf("Day: %d%n", date.getDay());

		date.setMonth(7);
		System.out.printf("Month: %d%n", date.getMonth());

		date.setYear(2016);
		System.out.printf("Year: %d%n", date.getYear());

		date.displayDate();
	}
}
