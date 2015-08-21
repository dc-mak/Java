// Ex 12.10: Recreate given GUI.

import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class ColorSelectFrame extends JFrame {
	private final JComboBox<String> colorPicker;
	private final String[] colors = {"RED"};
	private final JCheckBox selBackG;
	private final JCheckBox selForeG;
	private final JButton ok;
	private final JButton cancel;

	public ColorSelectFrame() {
		super("ColorSelect");
		final Box vBox = Box.createVerticalBox();
		final JPanel selection = new JPanel();
		final JPanel confirm   = new JPanel();

		add(vBox);
		colorPicker = new JComboBox<String>(colors);
		vBox.add(colorPicker);
		vBox.add(selection);
		vBox.add(confirm);
		
		selBackG = new JCheckBox("Background");
		selForeG = new JCheckBox("Foreground");
		selection.add(selBackG);
		selection.add(selForeG);

		ok     = new JButton("Ok");
		cancel = new JButton("Cancel");
		confirm.add(ok);
		confirm.add(cancel);
	}
}
