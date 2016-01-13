// Ex 22.7-8: Circle with JSlider and stats.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public final class CirclePanel extends JPanel {
	private int radius;
	
	public void setRadius(int radius) {
		if (radius < 0)
			return;
		this.radius = radius;
		repaint();
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 540);
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		final int W = getWidth();
		final int H = getHeight();

		g.setColor(Color.BLACK);
		g.drawOval(W/2-radius, H/2-radius, 2*radius, 2*radius);
	}
}
