import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.GradientPaint;
import java.awt.BasicStroke;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;

public final class DrawFrame extends JFrame {
	// Enums for selecting shape anyone?
	private final String[] shapeOpt = {"Line", "Rectangle", "Oval"};

	private final JButton undo = new JButton("Undo");
	private final JButton clear = new JButton("Clear");

	private final JComboBox<String> shapePicker =
		new JComboBox<String>(shapeOpt);

	private final JCheckBox filled = new JCheckBox("Filled");

	private final JCheckBox gradient = new JCheckBox("Use Gradient");

	private final JButton firstCol = new JButton("1st Colour...");
	private Color col1 = Color.BLACK;
	private Color col2 = Color.GRAY;
	private final JButton secndCol = new JButton("2nd Colour...");

	private final JTextField width      = new JTextField("10", 2);
	private final JTextField dashLength = new JTextField("15", 2);

	private final JCheckBox dashed = new JCheckBox("Dashed");

	private final JLabel status = new JLabel();
	private final DrawPanel draw = new DrawPanel(status);

	public DrawFrame() {
		super("Drawing");
		final JPanel topControls = new JPanel();
		topControls.add(undo);
		topControls.add(clear);
		topControls.add(shapePicker);
		topControls.add(filled);

		final JPanel botControls = new JPanel();
		botControls.add(gradient);
		botControls.add(firstCol);
		botControls.add(secndCol);
		botControls.add(width);
		botControls.add(dashLength);
		botControls.add(dashed);

		final Box tmp = Box.createVerticalBox();
		tmp.add(topControls);
		tmp.add(botControls);
		add(tmp, BorderLayout.NORTH);
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

		gradient.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				updatePaint();
			}});

		firstCol.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				col1 = getCol(col1);
				updatePaint();
			}});

		secndCol.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				col2 = getCol(col2);
				updatePaint();
			}});

		width.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				updateStroke();
			}});

		dashLength.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				updateStroke();
			}});

		dashed.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				updateStroke();
			}});
	}

	private Color getCol(Color col) {
		final Color tmp = JColorChooser.showDialog(DrawFrame.this, "Choose a colour", col);
		if (tmp == null)
			return col;
		return tmp;
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
