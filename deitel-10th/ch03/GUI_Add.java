// GUI Ex 3.1: Take two numbers and return their sum from a GUI.

import javax.swing.JOptionPane;

public class GUI_Add {
	public static void main(String[] args) {

		int a = Integer.parseInt(JOptionPane.showInputDialog("Enter first integer"));
		int b = Integer.parseInt(JOptionPane.showInputDialog("Enter second integer"));

		JOptionPane.showMessageDialog(null, String.format("Sum is %d", a+b));
	}
}
