// Ex 13.11-13: Draw a grid.

import javax.swing.JFrame;

public final class Grid {
	public static void main(String[] args){
		 JFrame app = new JFrame("Grids!");
		 app.add(new GridPanel());
		 app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 app.setSize(307, 330);
		 app.setResizable(false);
		 app.setVisible(true);
	}
}
