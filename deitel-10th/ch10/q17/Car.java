public final class Car implements CarbonFootprint {
	private final double litres;
	// http://enviroduck.com/carbon_footprint_calculations.php
	// 1 gallon = 20 pounds of CO2
	//	  => 1 l = 2.397 kg of CO2
	// theguardian.com/environment/green-living-blog/2010/sep/23/carbon-footprint-new-car
	private static final double BASE = 200000.0;
	private static final double RATE = 2.397; // kg/l

	public Car(double litres) {
		if (litres < 0)
			throw new IllegalArgumentException(
				"Litres must be greater than 0.");

		this.litres = litres;
	}

	public double getCarbonFootprint() { return litres * RATE + BASE; }
}
