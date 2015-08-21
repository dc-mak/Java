// Ex 13.6-8: Simple shapes.

import javax.swing.JFrame;

public final class Simple {
	public static void main(String[] args) {
		JFrame app = new JFrame("Simple shapes");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(new SimplePanel());
		app.setSize(300,300);
		app.setVisible(true);
	}
}
