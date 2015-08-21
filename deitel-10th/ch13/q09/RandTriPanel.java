// Ex 13.9: Draw random, distictly coloured triangles.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.Random;
import javax.swing.JPanel;

public final class RandTriPanel extends JPanel {
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Random r = new Random();

		Graphics2D g2 = (Graphics2D) g;

		final int X = getWidth();
		final int Y = getHeight();
		for (int i = 0; i < 8; i++) {
			/* Must be new for different colours */
			GeneralPath tri = new GeneralPath();
			tri.moveTo(r.nextInt(X), r.nextInt(Y));
			tri.lineTo(r.nextInt(X), r.nextInt(Y));
			tri.lineTo(r.nextInt(X), r.nextInt(Y));
			tri.closePath();
			g2.setColor(new Color(r.nextInt(256),
				r.nextInt(256), r.nextInt(256)));
			g2.fill(tri);
		}
	}
}
