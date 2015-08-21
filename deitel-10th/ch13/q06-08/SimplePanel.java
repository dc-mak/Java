// Ex 13.6-8: Simple shapes.

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;
import javax.swing.JPanel;

public final class SimplePanel extends JPanel { 
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		final Random rand = new Random();
		final int r = 10;
		final int x = getWidth()/2;
		final int y = getHeight()/2;

		for (int i = 1; i <= 8; i++) {
			g2D.setPaint(new Color(rand.nextInt(256),
				rand.nextInt(256), rand.nextInt(256)));
			g2D.setStroke(new BasicStroke(1+rand.nextInt(20)));
			g2D.draw(new Line2D.Double(rand.nextInt(2*x), rand.nextInt(2*y),
				rand.nextInt(2*x), rand.nextInt(2*y)));
		}

		// g2D.draw(new Ellipse2D.Double(
		//	  x-i*r, y-i*r, 2*i*r, 2*i*r));
		//g.draw(x-i*r, y-i*r, 2*i*r, 2*i*r, 0, 360);
	}
}
