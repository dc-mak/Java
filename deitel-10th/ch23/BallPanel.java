import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public final class BallPanel extends JPanel {

	public final List<Ball> balls = new ArrayList<>();
	public final Timer timer      = new Timer(30, e -> drawBalls());

	public BallPanel() {
		setBackground(Color.WHITE);
		timer.start();

		System.out.println("Why? - " + getGraphics() +
			" would like to be able to pass this to constructors of balls.");

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (balls.size() < 50) balls.add(new Ball(BallPanel.this));
			}});
	}

	// In a real thing, each ball would not have its own thread.
	// It actually ends up using more resources quickly.
	public void drawBalls() {
		for (Ball b : balls) SwingUtilities.invokeLater(b);
		// for (Ball b : balls) b.run();
	}
}
