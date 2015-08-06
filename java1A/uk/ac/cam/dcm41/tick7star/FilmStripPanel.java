package uk.ac.cam.dcm41.tick7star;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import uk.ac.cam.acr31.life.World;

public class FilmStripPanel extends JPanel {
	private World current;
	private final int GenLimit = 20;
	//I could use ArrayList I s'pose.
	private FilmStrip[] filmStrip = new FilmStrip[GenLimit];

	public FilmStripPanel() {
		for (int i = 0; i < GenLimit; i++) {
			filmStrip[i] = new FilmStrip();
			add(filmStrip[i]);
			add(Box.createHorizontalStrut(filmStrip[i].getZoom()));
		}
	}

	public void display(World w) {
		current = w;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS)); 
		for (int i = 0; i < GenLimit; i++) {
			filmStrip[i].display(current);
			current = current.nextGeneration(0);
		}
	}
}
