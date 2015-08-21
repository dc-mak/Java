// Ex 13.25: Tortoise and Hare.

import javax.swing.JFrame;

public final class Race {
	public static void main(String[] args) {
		JFrame app = new JFrame("Tortoise-and-Hare Race");
		app.add(new RacePanel());
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400, 440);
		app.setVisible(true);
	}
}
