import javax.swing.JFrame;

public final class Draw {
	public static void main(String[] args){
		DrawFrame app = new DrawFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(500, 300);
		app.setVisible(true);
	}
}
