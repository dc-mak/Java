package uk.ac.cam.dcm41.tick7star;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import uk.ac.cam.acr31.life.World;

public class FilmStrip extends GamePanel {
	public FilmStrip() {
		super();
		setZoom(getZoom()*7/10);
	}
}
