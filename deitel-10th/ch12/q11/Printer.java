// Ex 12.11: Recreate give GUI.

import javax.swing.JFrame;

public final class Printer {
	public static void main(String[] args){
		 final PrinterFrame app = new PrinterFrame();
		 app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 app.setSize(380,180);
		 app.setResizable(false);
		 app.setVisible(true);
	}
}
