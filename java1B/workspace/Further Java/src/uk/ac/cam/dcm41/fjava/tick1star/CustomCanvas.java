package uk.ac.cam.dcm41.fjava.tick1star;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

public final class CustomCanvas extends Canvas {

	private Image image;

	public void setImage(final Image image) {
		if (image != null) {
			this.image = image;
			repaint();
		} else {
			System.err.println("Null image received");
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
}
