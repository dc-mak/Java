import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.GradientPaint;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BasicStroke;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;

public final class ControlPanel extends JPanel {
	private final GridBagLayout layout = new GridBagLayout();
	private final GridBagConstraints constr = new GridBagConstraints();
	public static final Font font = new Font("SansSerif", Font.BOLD, 18);

	// Enums for selecting shape anyone?
	private final String[] shapeOpt = {"Line", "Rectangle", "Oval"};

	private final JButton undo = new JButton("Undo");
	private final JButton clear = new JButton("Clear");

	private final JComboBox<String> shapePicker =
		new JComboBox<String>(shapeOpt);

	private final JCheckBox filled = new JCheckBox("Filled");

	private final JCheckBox gradient = new JCheckBox("Gradient");

	private Color col1 = Color.BLACK;
	private Color col2 = Color.GRAY;

	private final JTextField width      = new JTextField("10", 5);
	private final JTextField dashLength = new JTextField("15", 5);

	private final JCheckBox dashed = new JCheckBox("Dashed");

	private DrawPanel draw;

	// ideally I would extend these buttons and include this as method
	private void clearListeners(AbstractButton button) {
		ActionListener[] als = button.getActionListeners();
		for (ActionListener al : als)
			button.removeActionListener(al);

		ItemListener[] ils = button.getItemListeners();
		for (ItemListener il : ils)
			button.removeItemListener(il);
	}

	// ditto here
	private <T> void clearListeners(JComboBox<T> combobox) {
		ItemListener[] ils = combobox.getItemListeners();
		for (ItemListener il : ils)
			combobox.removeItemListener(il);
	}

	public void setDrawPanel(DrawPanel draw) {
		this.draw = draw;
		updatePaint();
		updateStroke();

		clearListeners(undo);
		undo.addActionListener(e -> draw.clearLastShape());
		clearListeners(clear);
		clear.addActionListener(e -> draw.clearDrawing());

		clearListeners(shapePicker);
		shapePicker.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				draw.setShapeType(shapePicker.getSelectedIndex());
			}});

		clearListeners(filled);
		filled.addItemListener(e ->
				draw.setFilledShape(filled.isSelected()));
	}

	private final MyColorChooser chooser1;
	private final MyColorChooser chooser2;

	private void addComponent(Component comp, int row, int col, int width, int height) {
		constr.insets = new Insets(10, 10, 10, 10);
		constr.fill = GridBagConstraints.NONE;
		constr.gridx = col;
		constr.gridy = row;
		constr.gridwidth = width;
		constr.gridheight = height;
		layout.setConstraints(comp, constr);
		comp.setFont(font);
		add(comp);
	}

	public ControlPanel() {
		setLayout(layout);
		chooser1 = new MyColorChooser() {
			@Override
			public void colorChange() {
				col1 = chooser1.getRGB();
				updatePaint();
			}};

		chooser2 = new MyColorChooser() {
			@Override
			public void colorChange() {
				col2 = chooser2.getRGB();
				updatePaint();
			}};

		addComponent(clear, 0, 0, 2, 1);
		addComponent(undo, 0, 2, 2, 1);
		addComponent(shapePicker, 0, 4, 2, 1);

		addComponent(filled, 1, 0, 2, 1);
		addComponent(gradient, 1, 2, 2, 1);
		addComponent(dashed, 1, 4, 2, 1);

		addComponent(new JLabel("Line width"), 2, 1, 2, 1);
		addComponent(width, 2, 3, 2, 1);

		addComponent(new JLabel("Dash space"), 3, 1, 2, 1);
		addComponent(dashLength, 3, 3, 2, 1);

		addComponent(new JLabel("Colour 1:"), 4, 2, 2, 1);
		addComponent(chooser1, 6, 0, 6, 3);

		addComponent(new JLabel("Colour 2:"), 9, 2, 2, 1);
		addComponent(chooser2, 11, 0, 6, 3);

		gradient.addItemListener(e -> updatePaint());
		width.addActionListener(e -> updateStroke());
		dashLength.addActionListener(e -> updateStroke());
		dashed.addItemListener(e -> updateStroke());
	}

	private void updatePaint() {
		if (gradient.isSelected())
			draw.setCurrentPaint(new GradientPaint(0, 0, col1, 50, 50, col2, true));
		else
			draw.setCurrentPaint(col1);
	}

	private void updateStroke() {
		float w = 10;
		float[] d = new float[1];
		d[0] = 10;

		try { w = Float.parseFloat(width.getText());
			  if (w < 0.0f) w = 1.0f;
		} catch (NumberFormatException nfe) { /* w is already */ }
			
		try { d[0] = Float.parseFloat(dashLength.getText());
			  if (d[0] < 0.0f) d[0] = 1.0f;
		} catch (NumberFormatException nfe) { /* d[0] is already set */ }

		if (!dashed.isSelected())
			draw.setCurrentStroke(
				new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		else
			draw.setCurrentStroke(
				new BasicStroke(
					w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, d, 0));
	}
}
