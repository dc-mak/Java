public final class Cube extends ThreeDShape {
	private final double side;
	public Cube(double side) {
		if (side < 0.0)
			throw new IllegalArgumentException(
				"Side must be greater than 0.");

		this.side = side;
	}

	public double getArea() { return 6.0*side*side; }
	public double getVolume() { return side*side*side; }
}
