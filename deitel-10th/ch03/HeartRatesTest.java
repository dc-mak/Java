// Ex 3.16: Testing HeartRates.java. Ambiguous task statement, so I put

public class HeartRatesTest {
	public static void main(String[] args) {
		HeartRates person = new HeartRates("Jane", "Doe", 25, 8, 1996);

		System.out.printf("First name:\t\t%s%n", person.getFirstName());
		System.out.printf("Last name:\t\t%s%n", person.getLastName());
		System.out.printf("Date of Birth:\t\t%d/%d/%d%n", person.getDay(), person.getMonth(), person.getYear());

		System.out.printf("Age:\t\t\t%d%n", person.getAge());
		System.out.printf("Max Heart Rate:\t\t%d%n", person.getMaxHeartRate());
		System.out.printf("Target Heart Rate:\t%.2f-%.2f%n", person.getTargetLowerRate(), person.getTargetHigherRate());
	}
}

