// Ex 22.10: Dummy pannel that is drawn on.

import javax.swing.JPanel;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;

public final class SyncPanel extends JPanel {
	private String text;

	public <T> void setText(T text) {
		this.text = text.toString();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		final Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
		final Rectangle2D rect =
			g.getFontMetrics().getStringBounds(text, g);

		g2d.drawString(text, (float) (getWidth()/2.0-rect.getWidth()/2.0),
							 (float) (getHeight()/2.0+rect.getHeight()/2.0));
	}
}
