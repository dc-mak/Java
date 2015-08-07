// Ex 10.12/14/15

public abstract class Employee implements Payable {
	private final String firstName;
	private final String lastName;
	private final String socialSecurityNumber;

	// Yes, it should be final, but I did Ex 10.12 *after* Ex 10.14/15,
	// so changing the constructors for all the subclasses *and* the object
	// instatiations now is just not on.
	private boolean dateSet = false; // hack
	private /* final */ Date d;

	public void setDate(Date d) {
		if (!dateSet) {
			this.d = d;
			dateSet = true;
		}
	}

	public Date getDate() { return d; }

	public Employee(String firstName, String lastName,
			String socialSecurityNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getFirstName() { return firstName; }

	public String getLastName() { return lastName; }

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	@Override
	public String toString() {
		return String.format("%s %s%nsocial security number: %s",
				getFirstName(), getLastName(), getSocialSecurityNumber());
	}

	public abstract double earnings();

	public double getPaymentAmount() { return earnings(); }
}
