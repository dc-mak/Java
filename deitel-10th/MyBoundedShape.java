// GUI Exercise for Chapter 10 using inheritance.

import java.awt.Color;

public abstract class MyBoundedShape extends MyShape {
	private boolean filled;

	public boolean getFilled() { return filled; }

	public void setFilled(boolean f) { filled = f; }

	// implicit call to super();
	public MyBoundedShape() { this.filled = false; }

	public MyBoundedShape(int x1, int y1, int x2, int y2,
				Color color, boolean filled) {
		super(x1, y1, x2, y2, color);
		this.filled = filled;
	}

	public int getUpperLeftX() {
		return Math.min(getX1(),getX2());
	}

	public int getUpperLeftY() {
		return Math.min(getY1(),getY2());
	}

	public int getWidth() {
		return Math.abs(getX2()-getX1()); }

	public int getHeight() {
		return Math.abs(getY2()-getY1());
	}
}
