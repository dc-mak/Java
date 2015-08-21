// Ex 13.23: Turtle Graphics.

import javax.swing.JFrame;

public final class Turtle {
	public static void main(String[] args) {
		final TurtleFrame app = new TurtleFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400,300);
		app.setVisible(true);
	}
}
