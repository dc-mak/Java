// Ex 3.17: Target heart rate calculator.
// Task is ambiguous with how to return range using only techniques covered so far.

public class HealthProfile {
	private String firstName, lastName, gender;
	private int day, month, year; // Date of Birth
	private int height, weight; // inches, pounds

	public HealthProfile(String firstName, String lastName, String gender,
					   int day, int month, int year,
					   int height, int weight) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;

		if (day >= 1)
			this.day = day;
		if (month >= 1)
			this.month = month;
		if (year >= 0)
			this.year = year;

		if (height >= 0)
			this.height = height;
		if (weight >= 0)
			this.weight = weight;
	}

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }

	public int getDay() { return day; }
	public void setDay(int day) {
		if (day >= 1)
			this.day = day;
	}

	public int getMonth() { return month; }
	public void setMonth(int month) {
		if (month >= 1)
			this.month = month;
	}

	public int getYear() { return year; }
	public void setYear(int year) {
		if (year >= 0)
			this.year = year;
	}

	public int getHeight() { return height; }
	public void setHeight(int height) {
		if (height >= 0)
			this.height = height;
	}

	public int getWeight() { return weight; }
	public void setWeight(int weight) {
		if (weight >= 0)
			this.weight = weight;
	}

	public int getAge() { return 2015-year; }

	// 220 - age = 220 - 2015 + year = year - 1795
	public int getMaxHeartRate() { return year-1795; }

	public double getTargetLowerRate() {return 0.5*(year-1795); }
	public double getTargetHigherRate() {return 0.85*(year-1795); }

	public double getBMI() { return weight*703.0/(height*height); }
}
