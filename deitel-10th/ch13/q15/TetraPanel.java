// Ex 13.15: Draw a tetrahedron.

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

public final class TetraPanel extends JPanel {
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		final int S = 100;
		final Graphics2D g2 = (Graphics2D) g;

		GeneralPath face1 = new GeneralPath();
		face1.moveTo(0, S);
		face1.lineTo(3*S, S);
		face1.lineTo(3*S, 3*S);
		face1.closePath();
		g2.setColor(new Color(58, 127, 83));
		g2.fill(face1);

		GeneralPath face2 = new GeneralPath();
		face2.moveTo(3*S, S);
		face2.lineTo(4*S, 0);
		face2.lineTo(3*S, 3*S);
		face2.closePath();
		g2.setColor(new Color(133, 86, 58));
		g2.fill(face2);
		
		GeneralPath face3 = new GeneralPath();
		face3.moveTo(0,S);
		face3.lineTo(3*S, S);
		face3.lineTo(4*S, 0);
		face3.closePath();
		g2.setColor(new Color(115, 143, 103));
		g2.fill(face3);
	}
}
