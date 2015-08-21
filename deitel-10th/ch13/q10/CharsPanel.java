// Ex 13.10: Draw lots of characters.

import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;

public final class CharsPanel extends JPanel {
	private static final String[] names = {Font.DIALOG_INPUT,
		Font.MONOSPACED, Font.SERIF, Font.SANS_SERIF};
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Random r = new Random();

		final int X = getWidth();
		final int Y = getWidth();
		for (int i = 0; i < 400; i++) {
			g.setColor(new Color(r.nextInt(256), r.nextInt(256),
						r.nextInt(256)));
			g.setFont(new Font(names[r.nextInt(4)],
								r.nextInt(4), 9+r.nextInt(64)));
			g.drawString(Character.toString((char)(33+r.nextInt(94))),
							r.nextInt(X), r.nextInt(Y));
		}
	}
	// Printable ASCII Characters: 33-126
	// Font.style constants: plain (0), bold (1), italic (2) bold+italic (3)
	// Point size from 9 to 72
}
