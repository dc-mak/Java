public final class Triangle extends TwoDShape {
	private final double base;
	private final double height;

	public Triangle(double base, double height) {
		if (base < 0.0)
			throw new IllegalArgumentException(
				"Base must be greater than 0.");

		if (height < 0.0)
			throw new IllegalArgumentException(
				"Height must be greater than 0.");

		this.base = base;
		this.height = height;
	}
	
	public double getArea() { return 0.5*base*height; }
}
