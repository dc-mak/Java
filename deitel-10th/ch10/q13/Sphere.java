public final class Sphere extends ThreeDShape {
	private final double radius;
	public Sphere(double radius) {
		if (radius < 0.0)
			throw new IllegalArgumentException(
				"Radius must be greater than 0.");

		this.radius = radius;
	}

	public double getArea() { return 4.0*Math.PI*radius*radius; }
	public double getVolume() { return 4.0/3.0*Math.PI*radius*radius*radius; }
}
