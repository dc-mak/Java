public final class Bicycle implements CarbonFootprint {
	public static double BASE = 240.4;
	// tech.slashdot.org/story/11/08/14/1326247/Whats-the-Carbon-Footprint-of-Bicycling
	public static double RATE = 0.3735; // kg/km
	public final double km;

	public Bicycle(double km) {
		if (km < 0.0)
			throw new IllegalArgumentException(
				"Kilograms must be greater than 0.");

		this.km = km;
	}

	public double getCarbonFootprint() { return km*RATE + BASE; }
}
