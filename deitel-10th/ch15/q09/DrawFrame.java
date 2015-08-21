import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
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

	private final JButton load = new JButton("Load");
	private final JButton save = new JButton("Save");

	private final JLabel status = new JLabel();
	private final DrawPanel draw = new DrawPanel(status);

	public DrawFrame() {
		final JPanel topControls = new JPanel();
		topControls.add(undo);
		topControls.add(clear);
		topControls.add(colorPicker);
		topControls.add(shapePicker);
		topControls.add(filled);
		topControls.add(load);
		topControls.add(save);
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

		load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				loadShapes();
			}});

		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				saveShapes(draw.getShapes());
			}});
	}

	private void loadShapes() {
		final JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION)
			return;

		try (ObjectInputStream input = new ObjectInputStream(
				Files.newInputStream(chooser.getSelectedFile().toPath()))) {
			readShapes(input);
		} catch (IOException e) { 
			System.err.println("Error opening file.");
		}
	}

	private void readShapes(ObjectInputStream input) {
		final ArrayList<MyShape> shapes = new ArrayList<>();
		try {
			while (true)
				shapes.add((MyShape) input.readObject());
		} catch (EOFException e) {
			draw.setShapes(shapes);
		} catch (ClassNotFoundException e) {
			System.err.println("Invalid object type found in file.");
		} catch (IOException e) {
			System.err.println("Error reading from file.");
		}
	}

	private void saveShapes(ArrayList<MyShape> shapes) {
		final JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION)
			return;

		try (ObjectOutputStream output = new ObjectOutputStream(
				Files.newOutputStream(chooser.getSelectedFile().toPath()))) {
			for (MyShape shape : shapes) {
				output.writeObject(shape);
			}
		} catch (IOException e) { 
			System.err.println("Error saving shapes file.");
		}
	}
}
