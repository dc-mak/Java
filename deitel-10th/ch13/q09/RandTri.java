// Ex 13.9: Draw random, distictly coloured triangles.
import javax.swing.JFrame;

public final class RandTri {
	public static void main(String[] args){
		 JFrame app = new JFrame("Random Triangles");
		 app.add(new RandTriPanel());
		 app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 app.setSize(400,400);
		 app.setVisible(true);
	}
}
