// Ex 4.20: Employee pay based on hourly rate, hours and 1.5x for overtime.

public class Salary {
	private int hours;
	private double over_scale = 1.5;
	private double rate;

	public Salary(int hours, double rate) {
		this.hours =(hours >= 0 ? hours : 0);
		this.rate  =(rate  >= 0 ? rate  : 0);
	}

	public double getGrossPay() {
		int overtime =(hours > 40 ? hours - 40 : 0);
		return ((double) hours * rate +(double) overtime * over_scale * rate);
	}
}
