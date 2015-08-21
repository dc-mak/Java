// Ex 13.26: Draw an octoganal spiral.

import javax.swing.JFrame;

public final class Spiral {
	public static void main(String[] args) {
		JFrame app = new JFrame("Spiral");
		app.add(new SpiralPanel());
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400, 440);
		app.setVisible(true);
	}
}
