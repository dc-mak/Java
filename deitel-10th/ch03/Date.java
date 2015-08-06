// Ex 3.14:	Dates

public class Date {

	private int day, month, year;

	// Assume values are correct as per task.
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() { return day; }
	public void setDay(int day) { this.day = day; }

	public int getMonth() { return month; }
	public void setMonth(int month) { this.month = month; }

	public int getYear() { return year; }
	public void setYear(int year) { this.year = year; }

	public void displayDate() {
		System.out.printf("%d/%d/%d%n", day, month, year);
	}
}
