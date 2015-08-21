// Ex 13.15: Draw a tetrahedron.

import javax.swing.JFrame;

public final class Tetra {
	public static void main(String[] args){
		 JFrame app = new JFrame("Tetrahedron");
		 app.add(new TetraPanel());
		 app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 app.setSize(410,340);
		 app.setResizable(false);
		 app.setVisible(true);
	}
}
