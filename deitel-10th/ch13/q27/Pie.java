// Ex 13.27: Pie chart.

import javax.swing.JFrame;

public final class Pie {
	public static void main(String[] args) {
		try {
			JFrame app = new JFrame("Pie Chart");
			app.add(new PiePanel(Integer.parseInt(args[0]),
								 Integer.parseInt(args[1]),
								 Integer.parseInt(args[2]),
								 Integer.parseInt(args[3])));
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.setSize(400, 440);
			app.setVisible(true);
		} catch (IllegalArgumentException ie) {
			System.out.println("Integers above 0 only.");
		} catch (ArrayIndexOutOfBoundsException ae) {
			System.out.println("Too few arguments.");
		}
	}
}
