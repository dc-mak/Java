// Ex 22.7-8: Circle with JSlider and stats.

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public final class CircleFrame extends JFrame {
	private final JTextArea stats = new JTextArea();
	private final CirclePanel circle = new CirclePanel();
	private final JSlider radius =
		new JSlider(SwingConstants.HORIZONTAL, 100, 200, 150);

	private void updateStats() {
		final int r = radius.getValue();
		stats.setText(String.format("%s: %f%n%s: %f%n%s: %f%n",
					"Diameter", 2.0*r,
					"Area", Math.PI*r*r,
					"Circumference", 2*Math.PI*r));
	}

	public CircleFrame() {
		super("Cirlces");
		add(BorderLayout.NORTH, stats);
		add(circle);
		add(BorderLayout.SOUTH, radius);
		
		stats.setFont(new Font("SansSerif", Font.BOLD, 20));
		radius.addChangeListener(
			e -> {circle.setRadius(radius.getValue());
				  updateStats();});

		circle.setRadius(radius.getValue());
		updateStats();
	}

	public static void main(String[] args) {
		CircleFrame app = new CircleFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(500, 540);
		app.setVisible(true);
	}
}
