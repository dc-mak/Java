import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public final class DrawFrame extends JFrame {
	private final Color[] colors = {Color.BLACK,
		Color.BLUE,  Color.CYAN,       Color.DARK_GRAY, Color.GRAY,
		Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA,   Color.ORANGE,
		Color.PINK,  Color.RED,        Color.WHITE,     Color.YELLOW};

	private final String[] colorsOpt = {"Black",
		"Blue",   "Cyan",          "Dark Grey",  "Grey",
		"Green",  "Light Grey",  "Magenta",    "Orange",
		"Pink",   "Red",           "White",      "Yellow"};

	// Enums for selecting shape anyone?
	private final String[] shapeOpt = {"Line", "Rectangle", "Oval"};

	private final JButton undo = new JButton("Undo");
	private final JButton clear = new JButton("Clear");

	private final JComboBox<String> colorPicker =
		new JComboBox<String>(colorsOpt);

	private final JComboBox<String> shapePicker =
		new JComboBox<String>(shapeOpt);

	private final JCheckBox filled = new JCheckBox("Filled");
	private final JLabel status = new JLabel();
	private final DrawPanel draw = new DrawPanel(status);

	public DrawFrame() {
		final JPanel topControls = new JPanel();
		topControls.add(undo);
		topControls.add(clear);
		topControls.add(colorPicker);
		topControls.add(shapePicker);
		topControls.add(filled);
		add(topControls, BorderLayout.NORTH);
		add(draw, BorderLayout.CENTER);
		add(status, BorderLayout.SOUTH);

		undo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.clearLastShape();
			}});

		clear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				draw.clearDrawing();
			}});

		colorPicker.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					draw.setCurrentColor(colors[
						colorPicker.getSelectedIndex()]);
			}});

		shapePicker.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					draw.setShapeType(shapePicker.getSelectedIndex());
			}});

		filled.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				draw.setFilledShape(filled.isSelected());
			}});
	}
}
