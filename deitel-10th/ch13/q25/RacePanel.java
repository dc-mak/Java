// Ex 13.25: Tortoise and Hare.

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RacePanel extends JPanel implements ActionListener {
	private static final int DIV = 300;
	public static final Random rand = new Random();
	
	public final Timer timer = new Timer(50, this);
	public int tort;
	public int hare;

	public RacePanel() {
		timer.start();
		setBackground(Color.WHITE);
	}

	public void nextStep() {
		moveTortoise();
		moveHare();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if (!raceWon())
			nextStep();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		final double W = getWidth();
		final double H = getHeight();
		final double K = 0.1;
		final double C = 0.3;

		g2d.setColor(Color.BLACK);
		g2d.fill(new Arc2D.Double(0,0,2*W,2*H, 180-C*tort,-10*C, Arc2D.PIE));
		g.setFont(new Font("Sans Serif", Font.BOLD, 20));
		g.drawString("Tortoise", (int) (W*K), (int) (H*3*K));
		g2d.setColor(Color.ORANGE);
		g2d.fill(new Arc2D.Double(0,0,2*W,2*H, 180-C*hare,-10*C, Arc2D.PIE));
		g.drawString("Hare", (int) (W*K), (int) (H*2*K));
		g2d.setColor(Color.GREEN);
		g2d.fill(new Arc2D.Double(K*W, K*H, 2*(1-K)*W, 2*(1-K)*H, 90, 90, Arc2D.PIE));

		if (raceWon()) {
			g.setColor(Color.BLUE);
			g.drawString(hare > tort ? "Hare wins :(" :
						(hare < tort ? "TORTOISE WINS!" : "Oh, a tie"),
						(int) (W*K), (int) (H*K));
		}
	}

	public boolean raceWon() { return tort >= DIV-1 || hare >= DIV-1; }

	public void moveTortoise() {
		int move = 3;
		switch(rand.nextInt(10)) {
			case  5:
			case  6: move = -6; break;
			case  7:
			case  8:
			case  9: move =  1;
		}
		tort = tort + move < 0 ? 0 : tort + move;
	}

	public void moveHare() {
		int move = 1;
		switch(rand.nextInt(10)) {
			case  0:
			case  1: move =  0; break;
			case  2:
			case  3: move =  9; break;
			case  4: move = 12; break;
			case  8:
			case  9: move =  2; break;
		}
		hare = hare + move < 0 ? 0 : hare + move;
	}
}
