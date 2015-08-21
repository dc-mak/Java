// Ex 13.17: It said to take coordinates as well, presumably for the centre of
// the circle, but (1) ambiguous (2) automatic placement works better.

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public final class Circle {
	public static void main(String[] args){
		boolean loop = false;
		do {
			try {
				JFrame app = new JFrame("Circle");

				String num = JOptionPane.showInputDialog("Enter radius");

				if (num == null)
					return;

				app.add(new CirclePanel(Double.parseDouble(r)));
				app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				app.setSize(400,340);
				app.setVisible(true);
			} catch (IllegalArgumentException e) {
				loop = true;
			}
		} while (loop);
	}
}
