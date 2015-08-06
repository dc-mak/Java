// GUI Ex 6.2: Test harness for GUI_Random.

import javax.swing.JFrame;

public class GUI_RandomTest {
	public static void main(String[] args) {
		GUI_Random rand = new GUI_Random();
		JFrame app = new JFrame();

 		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(rand);
		app.setSize(600, 600);
		app.setVisible(true);
	}
}
