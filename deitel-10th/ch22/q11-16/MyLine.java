// GUI Exercise for Chapter 10: Inheritance.

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

public class MyLine extends MyShape {
	public MyLine() { /* implicit call to super(); */ }

	public MyLine(int x1, int y1, int x2, int y2, Paint paint, Stroke stroke) {
		super(x1,y1,x2,y2,paint,stroke);
	} 

	public void draw(Graphics2D g2d) {
		g2d.setPaint(getPaint());
		g2d.setStroke(getStroke());
		g2d.drawLine(getX1(), getY1(), getX2(), getY2());
	} 
}
