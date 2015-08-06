// Ex 5.1: Test for GUICircle.java

import javax.swing.JFrame;

public class GUI_CircleTest {
	public static void main(String[] args) {
		GUI_Circle c = new GUI_Circle();
		JFrame win = new JFrame();

		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.add(c);
		win.setSize(300, 300);
		win.setVisible(true);
	}
}
