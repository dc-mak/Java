public final class Circle extends TwoDShape {
	private final double radius;
	public Circle(double radius) {
		if (radius < 0.0)
			throw new IllegalArgumentException(
				"Radius must be greater than 0.");

		this.radius = radius;
	}
	
	public double getArea() { return Math.PI*radius*radius; }
}
