// GUI Ex 5.1: Draw 12 concentric circles, 10 pixels apart.

import java.awt.Graphics;
import javax.swing.JPanel;

public class GUI_Circle extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int height = getHeight();
		int width  = getWidth();

		// Centre
		int y = height/2;
		int x = width/2;

		for (int i = 0; i < 12; i++) {
			x -= 10;
			y -= 10;
			g.drawOval(x, y, 20*(i+1), 20*(i+1));
		}
	}
}
