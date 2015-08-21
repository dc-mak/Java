import javax.swing.JFrame;

public final class CrapsTest {
	public static void main(String[] args){
		CrapsFrame app = new CrapsFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(200, 140);
		app.setResizable(false);
		app.setVisible(true);
	}
}
