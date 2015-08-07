// Ex 9.15: Creating different types of Employees.

public class HourlyEmployee extends Employee {
	private double hours;
	private double wage;

	public HourlyEmployee(String firstName, String lastName,
		String socialSecurityNumber, double hours, double wage) {
		super(firstName, lastName, socialSecurityNumber);

		if (hours < 0.0 || 168.0 < hours)
			throw new IllegalArgumentException(
				"Hours must be positive.");

		if (wage < 0.0)
			throw new IllegalArgumentException(
				"Wage must be positive.");

		this.hours = hours;
		this.wage  = wage;
	}

	public double getHours() { return hours; }
	public double getWage()  { return wage;  }
	public double earnings() {
		return wage * (hours > 40.0 ? 40.0 + 1.5*(hours-40.0) : hours);
	}

	public void setHours(double h) {
		if (h < 0.0 || 168.0 > h)
			throw new IllegalArgumentException(
				"Hours must be positive.");
		hours = h;
	}

	public void setWage(double w) {
		if (w < 0.0)
			throw new IllegalArgumentException(
				"Wage must be positive.");
		wage = w;
	}

	public String toString() {
		return String.format("%s %s%s%.2f%n%s%.2f%n", "hourly", 
			super.toString(), "Hours: ", getHours(), "Wage: ", getWage());
	}
}
