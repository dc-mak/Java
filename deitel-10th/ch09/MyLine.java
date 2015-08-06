// GUI Exercise for Chapter 8.

import java.awt.Color;
import java.awt.Graphics;

public class MyLine {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;

	private int getX1() { return x1; }
	private int getX2() { return x2; }
	private int getY1() { return y1; }
	private int getY2() { return y2; }
	private Color getColor() { return color; }

	private int check(int a) { return a > 0 ? a : 0; }

	private void setX1 (int x) { x1 = check(x); }
	private void setX2 (int x) { x2 = check(x); }
	private void setY1 (int y) { y1 = check(y); }
	private void setY2 (int y) { y2 = check(y); }
	private void setColor(Color c) { color = c; }

	public MyLine(int x1, int y1, int x2, int y2, Color color) {
		setX1(x1);
		setX2(x2);
		setY1(y1);
		setY2(y2);
		setColor(color);
	} 

	public MyLine() { this(0,0,0,0,Color.BLACK); }

	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(getX1(), getY1(), getX2(), getY2());
	} 
}
