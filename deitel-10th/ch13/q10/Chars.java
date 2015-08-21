// Ex 13.10: Draw lots of characters.

import javax.swing.JFrame;

public final class Chars {
	public static void main(String[] args){
		 JFrame app = new JFrame("Characters");
		 app.add(new CharsPanel());
		 app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 app.setSize(600,600);
		 app.setVisible(true);
	}
}
