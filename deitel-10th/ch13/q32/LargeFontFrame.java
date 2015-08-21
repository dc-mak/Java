import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public final class LargeFontFrame extends JFrame {
	private final String[] fontNames = {"SansSerif", "Serif", "Monospaced"};
	private final JComboBox<String> fontPicker = new JComboBox<String>(fontNames);

	private final JButton inc   = new JButton("Increase Font Size");
	private final JButton dec   = new JButton("Decrease Font Size");
	private final JCheckBox bld = new JCheckBox("Bold");

	private final JTextArea text = new JTextArea("Enter text here");

	private final Font UI_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	private Font textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	private int fontSize = 18;

	public LargeFontFrame() {
		super("Large Font Text Editor");
		JPanel tmp = new JPanel();
		tmp.add(fontPicker);
		tmp.add(inc);
		tmp.add(dec);
		tmp.add(bld);
		add(tmp, BorderLayout.NORTH);
		add(text);

		text.setLineWrap(true);
		text.setWrapStyleWord(true);

		text.setFont(textFont);
		fontPicker.setFont(UI_FONT);
		inc.setFont(UI_FONT);
		dec.setFont(UI_FONT);
		bld.setFont(UI_FONT);

		inc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fontSize < 72)
					fontSize++;
				setTextAreaFont();
			}});

		dec.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fontSize > 10)
					fontSize--;
				setTextAreaFont();
			}});

		bld.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				setTextAreaFont();
			}});

		fontPicker.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				setTextAreaFont();
			}});
	}

	public void setTextAreaFont() {
		text.setFont(new Font((String)fontPicker.getSelectedItem(),
								bld.isSelected() ? Font.BOLD : Font.PLAIN,
								fontSize));
	}
}
