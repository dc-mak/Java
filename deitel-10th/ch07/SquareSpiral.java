// Ex 7.1: Draw a square spiral.

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class SquareSpiral extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		final int HEIGHT = getHeight();
		final int WIDTH  = getWidth();
		final int MAX    = Math.max(HEIGHT, WIDTH);
		final int DIV    = 60;

		// start centre
		int x = getWidth()/2;
		int y = getHeight()/2;
		g.setColor(Color.RED);

		for (int i = 0; x <= MAX && y <= MAX; i++) {
			if (i % 2 == 0) {
				// Down and then left
				g.drawLine(x, y, x, y+DIV*i);
				y += DIV*i;
				g.drawLine(x, y, x-DIV*i, y);
				x -= DIV*i;
			} else {
				// Up and then right
				g.drawLine(x, y, x, y-DIV*i);
				y -= DIV*i;
				g.drawLine(x, y, x+DIV*i, y);
				x += DIV*i;
			}
		}
	}
}
