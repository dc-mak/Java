// Ex 22.11: Creating a colour chooser.

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

public abstract class MyColorChooser extends JPanel {

	// high dpi Surface...
	private final GridBagLayout layout = new GridBagLayout();
	private final GridBagConstraints constr = new GridBagConstraints();

	private final JLabel redLabel;
	private final JLabel blueLabel;
	private final JLabel greenLabel;

	private final JTextField redText;
	private final JTextField blueText;
	private final JTextField greenText;

	private final JSlider redSlider;
	private final JSlider blueSlider;
	private final JSlider greenSlider;

	public abstract void colorChange();

	public final Color getRGB() {
		return new Color(redSlider.getValue(),
						 greenSlider.getValue(),
						 blueSlider.getValue());
	}

	private static JLabel colorLabel(String text) {
		final JLabel result = new JLabel(text, SwingConstants.CENTER);
		result.setFont(ControlPanel.font);
		return result;
	}

	private static JTextField colorTextField() {
		final JTextField result = new JTextField("0", 4);
		result.setFont(ControlPanel.font);
		return result;
	}

	private static JSlider colorSlider() {
		return new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
	}

	// cannot be static because of reference to colorChange()
	private void syncListeners(JSlider colorSlider, JTextField colorText) {
		colorText.addActionListener(
			event -> {
				try { colorSlider.setValue(Integer.parseInt(colorText.getText())); }
				catch (NumberFormatException e) { /* supress */ }
			});

		colorSlider.addChangeListener(
			event -> {
				colorText.setText(Integer.toString(colorSlider.getValue()));
				colorChange();
			});
	}

	// cannot be static: reference to constr and layout to simplify code
	private void addOne(Component comp, int fill, int row, int col,
						int width, int height) {
		constr.fill = fill;
		constr.gridy = row;
		constr.gridx = col;
		constr.gridwidth = width;
		constr.gridheight = height;
		layout.setConstraints(comp, constr);
		add(comp);
	}

	// cannot be static: reference to constr and layout to simplify code
	private void addOne(Component comp, int fill, int row, int col, int width) {
		constr.fill = fill;
		constr.gridy = row;
		constr.gridx = col;
		constr.gridwidth = width;
		constr.gridheight = 1;
		layout.setConstraints(comp, constr);
		add(comp);
	}

	private void addComponents(int row, JLabel colorLabel, JSlider colorSlider,
							   JTextField colorText) {
		constr.weightx = 0.1;
		addOne(colorLabel, GridBagConstraints.NONE, row, 0, 1);
		constr.weightx = 1.0;
		addOne(colorSlider, GridBagConstraints.HORIZONTAL, row, 1, 2);
		constr.weightx = 0.1;
		addOne(colorText, GridBagConstraints.NONE, row, 3, 1);
	}

	// compiler throws up if you pass references before they're
	// guaranteed to be initialised, even if to initialise them
	public MyColorChooser() {
		setLayout(layout);

		// Red controls row
		redLabel = colorLabel("RED");
		redSlider = colorSlider();
		redText = colorTextField();
		syncListeners(redSlider, redText);
		addComponents(0, redLabel, redSlider, redText);

		// Green controls row
		greenLabel =  colorLabel("GREEN");
		greenText = colorTextField();
		greenSlider = colorSlider();
		syncListeners(greenSlider, greenText);
		addComponents(1, greenLabel, greenSlider, greenText);

		// Blue controls
		blueLabel =  colorLabel("BLUE");
		blueSlider = colorSlider();
		blueText = colorTextField();
		syncListeners(blueSlider, blueText);
		addComponents(2, blueLabel, blueSlider, blueText);
	}

	public static void main(String[] args){
		JFrame test = new JFrame();
		MyColorChooser c = new MyColorChooser() {
			@Override
			public void colorChange() {
				System.out.print("c ");
			}};
		test.add(c);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setSize(500,500);
		test.setVisible(true);
	}
}
