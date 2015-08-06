// Ex 7.1: Test harness for drawing a square spiral (then a circular one).

import javax.swing.JFrame;

public class GUI_SpiralTest {
	public static void main (String[] args) {
		JFrame sq_app = new JFrame();
		SquareSpiral sq = new SquareSpiral();
		
		sq_app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sq_app.add(sq);
		sq_app.setSize(600,600);
		sq_app.setVisible(true);

		JFrame circ_app = new JFrame();
		CircleSpiral circ = new CircleSpiral();
		
		circ_app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		circ_app.add(circ);
		circ_app.setSize(600,600);
		circ_app.setVisible(true);
	}
}
