// Ex 8.9: Modify to allow day incrementing.

public class Date  {
	private int day;
	private int month;
	private int year;
	
	public int getDay()   { return day;   }
	public int getMonth() { return month; }
	public int getYear()  { return year;  }

	private static final int[] daysPerMonth =
			{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public Date(int day, int month, int year) {

		if (day <= 0 || 
				(day > daysPerMonth[month] && !(month == 2 && day == 29)))
			throw new IllegalArgumentException("day (" + day + 
					") out-of-range for the specified month and year");

		if (month <= 0 || month > 12)
			throw new IllegalArgumentException(
					"month (" + month + ") must be 1-12");

		if (month == 2 && day == 29 && !(year % 400 == 0 || 
					(year % 4 == 0 && year % 100 != 0)))
			throw new IllegalArgumentException("day (" + day +
					") out-of-range for the specified month and year");

		if (year < 0)
			throw new IllegalArgumentException(
					"year (" + year + ") must be positive");

		this.month = month;
		this.day = day;
		this.year = year;

		System.out.printf(
				"Date object constructor for date %s%n", this);
	} 

	public static boolean leapYear(int y) {
		return y % 4 == 0 && y % 100 != 0 || y % 400 == 0;
	}

	public void nextDay() {
		if (leapYear(year) && month == 2 && day > 27)
			if (day == 28)
				day = 29;
			else // (day == 29)
				day = 1;
		else
			if (day % daysPerMonth[month] == 0) {
				day = 1;
				nextMonth();
			} else
				day++;
	}

	public void nextMonth() {
		if (month == 12) {
			month = 1;
			nextYear();
		} else
			month++;
	}

	public void nextYear() { year++; }

	public String toString() { 
		return String.format("%d/%d/%d", day, month, year); 
	} 
}
