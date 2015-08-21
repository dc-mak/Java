// Ex 12.12/13: Basic temparature conversion app.

import javax.swing.JFrame;

public final class Temp {
	public static void main(String[] args){
		 final TempFrame app = new TempFrame();
		 app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 app.setSize(250,150);
		 app.setResizable(false);
		 app.setVisible(true);
	}
}
