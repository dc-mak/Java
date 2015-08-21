// Ex 13.27: Pie chart.

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.Color;

public final class PiePanel extends JPanel {
	private final int a;
	private final int b;
	private final int x;
	private final int y;

	public PiePanel(int a, int b, int x, int y) {
		if (a < 0 || b < 0 || x < 0 || y < 0)
			throw new IllegalArgumentException(
				"Quantities must be greater than 0.");
		this.a = a;
		this.b = b;
		this.x = x;
		this.y = y;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		final int total = a + b + x + y;

		final double a_rad = 360.0*a/total;
		final double b_rad = 360.0*b/total;
		final double x_rad = 360.0*x/total;
		final double y_rad = 360.0*y/total;

		final int W = getWidth();
		final int H = getHeight();

		g2.setColor(Color.BLACK);
		g2.fill(new Arc2D.Double(0.05*W, 0.05*H, 0.9*W, 0.9*H, 0, a_rad, Arc2D.PIE));
		g2.setColor(Color.ORANGE);
		g2.fill(new Arc2D.Double(0.05*W, 0.05*H, 0.9*W, 0.9*H, a_rad, b_rad, Arc2D.PIE));
		g2.setColor(Color.RED);
		g2.fill(new Arc2D.Double(0.05*W, 0.05*H, 0.9*W, 0.9*H, a_rad+b_rad, x_rad, Arc2D.PIE));
		g2.setColor(Color.GREEN);
		g2.fill(new Arc2D.Double(0.05*W, 0.05*H, 0.9*W, 0.9*H, a_rad+b_rad+x_rad, y_rad, Arc2D.PIE));
	}
}
