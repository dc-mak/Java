// GUI Ex 6.2: Draw 10 random shapes .

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GUI_Random extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Random rand = new Random();
		int height = getHeight();
		int width  = getWidth();

		for (int i = 0; i < 10; i++) {
			g.setColor(new Color(rand.nextInt(255), rand.nextInt(255),
							 rand.nextInt(255)));
			if (rand.nextInt() % 2 == 0)
				g.fillOval(rand.nextInt(height), rand.nextInt(width),
						   rand.nextInt(height/2), rand.nextInt(width/2));
			else
				g.fillRect(rand.nextInt(height), rand.nextInt(width),
						   rand.nextInt(height/2), rand.nextInt(width/2));
		}
	}
}
