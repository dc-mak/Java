// Ex 3.16: Target heart rate calculator.
// Task is ambiguous with how to return range using only techniques covered so far.

public class HeartRates {
	private String firstName, lastName;
	private int day, month, year; // Date of Birth

	public HeartRates(String firstName, String lastName,
					   int day, int month, int year) {
		this.firstName = firstName;
		this.lastName = lastName;
		// Assume correct
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	public int getDay() { return day; }
	public void setDay(int day) { this.day = day; }

	public int getMonth() { return month; }
	public void setMonth(int month) { this.month = month; }

	public int getYear() { return year; }
	public void setYear(int year) { this.year = year; }

	public int getAge() { return 2015-year; }

	// 220 - age = 220 - 2015 + year = year - 1795
	public int getMaxHeartRate() { return year-1795; }

	public double getTargetLowerRate() {return 0.5*(year-1795); }
	public double getTargetHigherRate() {return 0.85*(year-1795); }
}
