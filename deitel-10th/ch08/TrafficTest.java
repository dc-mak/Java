// Ex 8.10: Enums with traffic lights.

public class TrafficTest {
	private enum Traffic {
		RED(10),
		AMBER(11),
		GREEN(12);
		int duration;
		Traffic(int d) { duration = d; }
	}

	public static void main(String[] args) {
		System.out.println(Traffic.RED);
		System.out.println(Traffic.AMBER);
		System.out.println(Traffic.GREEN);
		System.out.println(Traffic.RED.duration);
		System.out.println(Traffic.AMBER.duration);
		System.out.println(Traffic.GREEN.duration);
	}
}
