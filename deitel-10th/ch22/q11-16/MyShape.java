// GUI Exercise for Chapter 10: Inheritance.

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

public abstract class MyShape {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Paint paint;
	private Stroke stroke;

	public int getX1() { return x1; }
	public int getX2() { return x2; }
	public int getY1() { return y1; }
	public int getY2() { return y2; }
	public Paint getPaint() { return paint; }
	public Stroke getStroke() { return  stroke; }

	private static int check(int a) { return a > 0 ? a : 0; }

	public void setX1 (int x) { x1 = check(x); }
	public void setY1 (int y) { y1 = check(y); }
	public void setX2 (int x) { x2 = check(x); }
	public void setY2 (int y) { y2 = check(y); }
	public void setPaint(Paint p) { paint = p; }
	public void setStroke(Stroke s) { stroke = s; }

	public MyShape() { this(0, 0, 0, 0, Color.BLACK, new BasicStroke(5.0f)); }

	public MyShape(int x1, int y1, int x2, int y2, Paint paint, Stroke stroke) {
		this.x1     = check(x1);
		this.y1     = check(y1);
		this.x2     = check(x2);
		this.y2     = check(y2);
		this.paint  = paint;
		this.stroke = stroke;
	} 

	public abstract void draw(Graphics2D g);

}
