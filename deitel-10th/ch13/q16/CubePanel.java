// Ex 13.16: Draw a cube.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

public final class CubePanel extends JPanel {
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		final int S = 50; // Scale

		final int[] X = {0, 3, 5, 8};
		final int[] Y = {0, 1, 5, 6};

		final int[] x1 = {X[0], X[2], X[2], X[0]};
		final int[] y1 = {Y[1], Y[1], Y[3], Y[3]};
		GeneralPath f1 = new GeneralPath();
		f1.moveTo(x1[0]*S, y1[0]*S);
		for (int i = 1; i < x1.length; i++)
			f1.lineTo(x1[i]*S, y1[i]*S);
		f1.closePath();
		g2.setColor(new Color(58, 127, 83));
		g2.fill(f1);

		final int[] x2 = {X[0], X[2], X[3], X[1]};
		final int[] y2 = {Y[1], Y[1], Y[0], Y[0]};
		GeneralPath f2 = new GeneralPath();
		f2.moveTo(x2[0]*S, y2[0]*S);
		for (int i = 1; i < x2.length; i++)
			f2.lineTo(x2[i]*S, y2[i]*S);
		f2.closePath();
		g2.setColor(new Color(133, 86, 58));
		g2.fill(f2);

		final int[] x3 = {X[2], X[2], X[3], X[3]};
		final int[] y3 = {Y[1], Y[3], Y[2], Y[0]};
		GeneralPath f3 = new GeneralPath();
		f3.moveTo(x3[0]*S, y3[0]*S);
		for (int i = 1; i < x3.length; i++)
			f3.lineTo(x3[i]*S, y3[i]*S);
		f3.closePath();
		g2.setColor(new Color(115, 143, 103));
		g2.fill(f3);
	}
}
