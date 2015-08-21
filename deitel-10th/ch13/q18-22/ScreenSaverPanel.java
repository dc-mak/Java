// Ex 13.18-22: Screen savers!

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.JPanel;

public final class ScreenSaverPanel extends JPanel implements ActionListener {
	private int LINES;
	private final Random r         = new Random();
	private final JTextField input = new JTextField("Lines", 3);
	private final Timer timer      = new Timer(1000, this);

	public ScreenSaverPanel() {
		timer.start();
		add(input);
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try { int tmp = Integer.parseInt(input.getText());
					  if (tmp > 0) LINES = tmp;
				} catch (NumberFormatException nfe) { /* suppress */ }
			}});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		final int W = getWidth()/2;
		final int H = getHeight()/2;

		for (int i = 0; i < LINES; i++) {
			g2d.setPaint(randGradient(W,H));
			switch (r.nextInt(2)) {
				case  0: g2d.draw(randShape(W,H)); break;
				default: g2d.fill(randShape(W,H));
			}
		}
	}

	private GradientPaint randGradient(int W, int H) {
		return new GradientPaint(
			r.nextInt(W), r.nextInt(H),
			new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)),
			r.nextInt(W), r.nextInt(H),
			new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)),
			r.nextInt(2) == 0);
	}

	private Shape randShape(int W, int H) {
		switch (r.nextInt(2)) {
			case  0: return new Rectangle2D.Double(
							 r.nextInt(3*W/2), r.nextInt(3*H/2),
							 r.nextInt(W), r.nextInt(H));
			default: return new Ellipse2D.Double(
							 r.nextInt(3*W/2), r.nextInt(3*H/2),
							 r.nextInt(W), r.nextInt(H));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
// g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
// g.drawLine(r.newtInt(W), r.newtInt(H), r.newtInt(W), r.newtInt(H));
