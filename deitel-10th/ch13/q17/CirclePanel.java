// Ex 13.17: Circle stats.

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Font;

public final class CirclePanel extends JPanel {
	private final double radius;
	public CirclePanel(double radius) {
		
		if (radius < 0)
			throw new IllegalArgumentException(
				"Radius must be greater than 0.");
		this.radius = radius;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;

		g2d.draw(new Ellipse2D.Double(radius, radius/2, 2*radius, 2*radius));

		g.setFont(new Font("Sans Serif", Font.BOLD, 20));

		g.drawString(String.format("Diameter: %.2f", 2*radius),
					(int) radius, (int) (3*radius+10));

		g.drawString(String.format("Circumference: %.2f", 2*Math.PI*radius),
					(int) radius, (int) (3*radius+40));

		g.drawString(String.format("Diameter: %.2f", Math.PI*radius*radius),
					(int) radius, (int) (3*radius+70));
	}
}
