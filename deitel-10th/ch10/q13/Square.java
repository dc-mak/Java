public final class Square extends TwoDShape {
	private final double side;
	public Square(double side) {
		if (side < 0.0)
			throw new IllegalArgumentException(
				"Side must be greater than 0.");

		this.side = side;
	}
	
	public double getArea() { return side*side; }
}
