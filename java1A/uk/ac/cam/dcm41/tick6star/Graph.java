package uk.ac.cam.dcm41.tick6star;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.FontMetrics;
import java.awt.Graphics;
import uk.ac.cam.acr31.life.World;

public class Graph extends JPanel {

	private final JLabel Name;
	private final Plot plot;

	public Graph(String Title, boolean filled, GraphData g) {
		super();
		setLayout(new BorderLayout());
		add(Name = new JLabel(Title, SwingConstants.CENTER), BorderLayout.NORTH);

		plot = new Plot(filled, g);
		add(plot, BorderLayout.CENTER);
	}
}
