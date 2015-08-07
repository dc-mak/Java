// Assume a regular tetrahedron.

public final class Tetrahedron extends ThreeDShape {
	private final double side;

	public Tetrahedron(double side) {
		if (side < 0.0)
			throw new IllegalArgumentException(
				"Side height must be greater than 0.");

		this.side = side;
	}

	public double getArea() { return Math.sqrt(3.0)*side*side; }
	public double getVolume() { return Math.sqrt(2.0)/12.0*side*side*side; }
}
