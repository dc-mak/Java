import javax.swing.JFrame;

public final class BallBounce {
	public static void main(String[] args) {
		JFrame app = new JFrame("Bounce with Swing Concurrency :-)");
		app.add(new BallPanel());
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400, 430);
		app.setVisible(true);
	}
}
