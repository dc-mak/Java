// Ex 13.28-30: Selecting shapes to be randomly drawn 20 times.

import javax.swing.JPanel;
import java.util.Random;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;

public final class ShapePanel extends JPanel {
	public static final int LIM = 20;
	public static final Random r = new Random();
	public final Shape[] shapes = new Shape[LIM];
	public S shapeType;
	public Color color;

	public void setShape(S shapeType, Color color) {
		this.shapeType = shapeType;
		this.color = color;
		repaint();
	}

	public ShapePanel(S shapeType, Color color)	  {
		this.shapeType = shapeType;
		this.color = color;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(color);

		final int W = getWidth();
		final int H = getHeight();

		switch (shapeType) {
			case LINE: for (int i = 0; i < shapes.length; i++)
						   shapes[i] = new Line2D.Double(
							   r.nextInt(W), r.nextInt(H),
							   r.nextInt(W), r.nextInt(H));
					   break;
			case RECT: for (int i = 0; i < shapes.length; i++)
						   shapes[i] = new Rectangle2D.Double(
							   r.nextInt(W), r.nextInt(H),
							   r.nextInt(W)/2.0, r.nextInt(H)/2.0);
					   break;
			case OVAL: for (int i = 0; i < shapes.length; i++)
						   shapes[i] = new Ellipse2D.Double(
							   r.nextInt(W), r.nextInt(H),
							   r.nextInt(W)/2.0, r.nextInt(H)/2.0);
		}

		for (Shape s : shapes)
			if (s instanceof Line2D)
				g2d.draw(s);
			else
				g2d.fill(s);
	}
}
