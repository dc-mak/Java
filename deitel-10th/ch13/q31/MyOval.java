// GUI Exercise for Chapter 10: Inheritance.

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

public class MyOval extends MyBoundedShape {
	public MyOval() { /* implicit super(); */ }

	public MyOval(boolean filled) { super(filled); }

	public MyOval(int x1, int y1, int x2, int y2,
			Paint paint, Stroke stroke, boolean filled) {
		super(x1, y1, x2, y2, paint, stroke, filled);
	}

	public void draw(Graphics2D g2d) {
		g2d.setStroke(getStroke());
		g2d.setPaint(getPaint());
		if (getFilled())
			g2d.fillOval(getUpperLeftX(), getUpperLeftY(),
						getWidth(), getHeight());
		else
			g2d.drawOval(getUpperLeftX(), getUpperLeftY(),
						getWidth(), getHeight());
	} 
}
