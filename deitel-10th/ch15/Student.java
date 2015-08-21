// Ex 15.6: Student object to serialize.

import java.io.Serializable;

public final class Student implements Serializable {
	private final int id;
	private final String firstName;
	private final String lastName;
	private final double[] grade = new double[3];

	public Student(int id, String firstName, String lastName,
			double grade0, double grade1, double grade2) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		grade[0] = grade0;
		grade[1] = grade1;
		grade[2] = grade2;
	}
}
