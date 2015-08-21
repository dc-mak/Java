import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;

public final class FontFrame extends JFrame {
	private final JButton inc = new JButton("Increase");
	private final JButton dec = new JButton("Decrease");
	private final JTextArea text = new JTextArea(
		"The quick brown fox jumps over the lazy dog.", 40, 40);

	private final String family = "Default";
	private final int style = Font.PLAIN;
	private int fontSize = 9;

	public FontFrame() {
		super("Font");
		final JPanel control = new JPanel();
		control.add(inc);
		control.add(dec);
		add(control, BorderLayout.SOUTH);
		add(text);
		text.setLineWrap(true);
		text.setFont(new Font(family, style, fontSize));

		inc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fontSize < 72)
					text.setFont(new Font(family, style, ++fontSize));
			}});

		dec.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fontSize > 9)
					text.setFont(new Font(family, style, --fontSize));
			}});
	}
}
