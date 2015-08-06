// GUI Exercise for Chapter 8.

import java.awt.Color;
import java.awt.Graphics;

public class MyRectangle {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;
	private boolean filled;

	private int getX1() { return x1; }
	private int getX2() { return x2; }
	private int getY1() { return y1; }
	private int getY2() { return y2; }
	private Color getColor() { return color; }
	private boolean getFilled() { return filled; }

	private int check(int a) { return a > 0 ? a : 0; }

	private void setX1 (int x) { x1 = check(x); }
	private void setX2 (int x) { x2 = check(x); }
	private void setY1 (int y) { y1 = check(y); }
	private void setY2 (int y) { y2 = check(y); }
	private void setColor(Color c) { color = c; }
	private void setFilled(boolean b) { filled = b; }

	// Thought you weren't supposed to call setters from constructors?
	public MyRectangle(int x1, int y1, int x2, int y2,
						Color color, boolean filled) {
		setX1(x1);
		setX2(x2);
		setY1(y1);
		setY2(y2);
		setColor(color);
		setFilled(filled);
	} 

	public MyRectangle() { this(0,0,0,0,Color.BLACK,false); }

	public int getUpperLeftX() { return Math.min(x1,x2); }
	public int getUpperLeftY() { return Math.min(y1,y2); }
	public int getWidth() { return Math.abs(x2-x1); }
	public int getHeight() { return Math.abs(y2-y1); }

	public void draw(Graphics g) {
		g.setColor(color);
		if (filled)
			g.fillRect(getUpperLeftX(), getUpperLeftY(),
						getWidth(), getHeight());
		else
			g.drawRect(getUpperLeftX(), getUpperLeftY(),
						getWidth(), getHeight());
	} 
}
