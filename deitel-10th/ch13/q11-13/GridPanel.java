// Ex 13.11-13: Draw a grid.

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public final class GridPanel extends JPanel {
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		final int DIM = 10;
		final int THK = 30;

		for (int i = 0; i <= DIM; i++) {
			g2.draw(new Rectangle2D.Double(0, 0, i*THK, DIM*THK));
			g2.draw(new Rectangle2D.Double(0, 0, DIM*THK, i*THK));
		}
	}
	// g.drawRect(0, 0, i*THK, DIM*THK);
	// g.drawRect(0, 0, DIM*THK, i*THK);
	// g2.draw(new Line2D.Double(i*THK, 0, i*THK, DIM*THK));
	// g2.draw(new Line2D.Double(0, i*THK, DIM*THK, i*THK));
	// g.drawLine(i*THK, 0, i*THK, DIM*THK);
	// g.drawLine(0, i*THK, DIM*THK, i*THK);
}
