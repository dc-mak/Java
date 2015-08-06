package uk.ac.cam.acr31.life;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WorldViewer extends JFrame {

	private static final long serialVersionUID = -3555304350705631171L;

	public static final int CELLSIZE = 10;

	private BufferedImage background = new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
	private JScrollPane scroller;
	private JLabel generation;
	
	public WorldViewer() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("World Viewer");
		setLayout(new BorderLayout());
		scroller = new JScrollPane(new JPanel() {
			private static final long serialVersionUID = 5511103440043536060L;

			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(background.getWidth(), background.getHeight());
			}
		});
		add(scroller, BorderLayout.CENTER);
		generation = new JLabel("Generation 0 / Population 0");
		add(generation,BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}

	public void show(World w) {
		background = new BufferedImage(w.getWidth()*CELLSIZE, w.getHeight()*CELLSIZE, BufferedImage.TYPE_INT_RGB);	
		Graphics g = background.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, background.getWidth(), background.getHeight());
		w.draw(g, background.getWidth(),background.getHeight());
		g.dispose();
		scroller.revalidate();
		generation.setText("Generation "+w.getGeneration()+" / Population "+w.getPopulation());
		repaint();
	}

	public void close() {
		setVisible(false);
		dispose();
	}
}
