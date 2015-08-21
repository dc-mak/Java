// Ex 12.14: Basic number guessing game.

import javax.swing.JFrame;

public final class Guess {
	public static void main(String[] args){
		 final GuessFrame app = new GuessFrame();
		 app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 app.setSize(350,60);
		 app.setResizable(false);
		 app.setVisible(true);
	}
}
