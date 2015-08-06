package uk.ac.cam.dcm41.tick7star;

import javax.swing.JPanel;
import uk.ac.cam.acr31.life.World;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Point;

public abstract class GameBoardPanel extends GamePanel {

	public GameBoardPanel() {
		super();
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				Point p = me.getPoint();
				int zoom = getZoom();
				int x = p.x/zoom;
				int y = p.y/zoom;
				onMousePressed(x, y);
			}
		});
	}
	
	protected abstract void onMousePressed(int x, int y);
	
}
