// Ex 13.18-22: Screen savers!

import javax.swing.JOptionPane;
import javax.swing.JFrame;

public final class ScreenSaver {
	public static void main(String[] args){
		JFrame app = new JFrame("Screen Saver");
		app.add(new ScreenSaverPanel());
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400,340);
		app.setVisible(true);
	}
}
