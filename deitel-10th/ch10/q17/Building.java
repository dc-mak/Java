public final class Building implements CarbonFootprint {
	// epa.gov/cleanenergy/energy-resources/refs.html
	// greenrationbook.org.uk/resources/footprints-building-construction/
	private static final double BASE = 689610.0; // house
	private static final double RATE = 0.689551; // kg / kWh
	private final double kWh;
	
	public Building(double kWh) {
		if (kWh < 0)
			throw new IllegalArgumentException(
				"kWh must be greater than 0.");
		this.kWh = kWh;
	}

	public double getCarbonFootprint() { return kWh*RATE + BASE; }
}
