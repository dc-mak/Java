// Ex 8.14: Extra output and input formats.

public class Date2  {
	private int day;
	private int month;
	private int year;
	
	public int getDay()   { return day;   }
	public int getMonth() { return month; }
	public int getYear()  { return year;  }

	private static final int[] daysPerMonth =
		{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	private static final String[] monthStrings = 
		{"", "January", "February", "March", "April", "May", "June",
		 "July", "August", "September", "October", "November", "December"};

	public Date2(String month, int day, int year) {
		int m;
		for (m = 1; m < monthStrings.length; m++)
			if (monthStrings[m].equals(month)) break;

		if (m > 12)
			throw new IllegalArgumentException(
				"Month supplied is not valid.");

		if (day <= 0 || 
				(day > daysPerMonth[m] && !(m == 2 && day == 29)))
			throw new IllegalArgumentException("day (" + day + 
					") out of range for the specified month and year");

		if (m == 2 && day == 29 && !(year % 400 == 0 || 
					(year % 4 == 0 && year % 100 != 0)))
			throw new IllegalArgumentException("day (" + day +
					") out of range for the specified month and year");

		if (year < 0)
			throw new IllegalArgumentException(
					"year (" + year + ") must be positive");

		this.month = m;
		this.day = day;
		this.year = year;
	}

	public Date2(int days, int year) {

		if (days <= 0)
			throw new IllegalArgumentException("day (" + day + 
					") out of range for the specified month and year");

		if (year < 0)
			throw new IllegalArgumentException(
					"year (" + year + ") must be positive");
		int m;
		int total = 0;
		for (m = 1; m < daysPerMonth.length; m++) {
			int next = daysPerMonth[m] + (leapYear(year) && m == 2 ? 1 : 0);
			if (days <= total + next)
				break;
			else
				total += next;
		}

		if (m > 12)
			throw new IllegalArgumentException(
					"Days out of range for specified month and year");

		this.month = m;
		this.day = days - total;
		this.year = year;
	}

	public Date2(int day, int month, int year) {

		if (day <= 0 || 
				(day > daysPerMonth[month] && !(month == 2 && day == 29)))
			throw new IllegalArgumentException("day (" + day + 
					") out of range for the specified month and year");

		if (month <= 0 || month > 12)
			throw new IllegalArgumentException(
					"month (" + month + ") must be 1-12");

		if (month == 2 && day == 29 && !(year % 400 == 0 || 
					(year % 4 == 0 && year % 100 != 0)))
			throw new IllegalArgumentException("day (" + day +
					") out of range for the specified month and year");

		if (year < 0)
			throw new IllegalArgumentException(
					"year (" + year + ") must be positive");

		this.month = month;
		this.day = day;
		this.year = year;
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

	public String toString2() {
		return monthStrings[month]+" "+day+", "+year;
	}

	public String toString3() {
		int days = day + ((leapYear(year) && month > 2) ? 1 : 0);
		for (int i = 0; i < month; i++)
			days += daysPerMonth[i];
		return days+" "+year;
	}
}
