// Ex 13.16: Draw a cube.

import javax.swing.JFrame;

public final class Cube {
	public static void main(String[] args){
		JFrame app = new JFrame("Cube");
		app.add(new CubePanel());
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(400,340);
		app.setResizable(false);
		app.setVisible(true);
	}
}
