// Ex 13.26: Draw an octoganal spiral.

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

public final class SpiralPanel extends JPanel {
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;

		final int LIM = 19;
		final double t = Math.PI/4;
		final double K = 40;
		final double X = (double) getWidth()/2;
		final double Y = (double) getHeight()/2;

		g2d.translate((int) X, (int) Y);

		int[] xP = new int[LIM];
		int[] yP = new int[LIM];
		for (int i = 0; i < LIM; i++) {
			xP[i] = (int) (K*(i+8)/8*Math.cos(i*t));
			yP[i] = (int) (K*(i+8)/8*Math.sin(i*t));
		}
		g2d.drawPolyline(xP, yP, LIM);
	}
}
