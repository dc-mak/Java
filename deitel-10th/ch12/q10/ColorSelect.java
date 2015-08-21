// Ex 12.10: Recreate given GUI.

import javax.swing.JFrame;

public final class ColorSelect {
	public static void main(String[] args){
		final ColorSelectFrame app = new ColorSelectFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(300,150);
        app.setVisible(true);
	}
}
