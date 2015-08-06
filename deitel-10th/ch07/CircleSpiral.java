// Ex 7.1: Draw a square spiral.

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class CircleSpiral extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		final int HEIGHT = getHeight();
		final int WIDTH  = getWidth();
		final int MAX    = Math.max(HEIGHT, WIDTH);
		final int DIV    = 30;

		// start centre
		int x = getWidth()/2;
		int y = getHeight()/2 - DIV;
		g.setColor(Color.BLUE);

		for (int i = 1; 2*(i-3)*DIV <= MAX ; i++) {
			if (i % 2 == 1) {
				// Clockwise
				g.drawArc(x, y, 2*i*DIV, 2*i*DIV, 0, 180);
				x -= 2*DIV;
				y -= DIV;
			} else {
				// Counter-clockwise
				g.drawArc(x, y, 2*i*DIV, 2*i*DIV, 0, -180);
				y -= DIV;
			}
		}
	}
}
