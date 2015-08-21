// Ex 13.28-30: Selecting shapes to be randomly drawn 20 times.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public final class ShapeFrame extends JFrame {
	private final Color[] colArr = {Color.BLACK,
		Color.BLUE,  Color.CYAN,       Color.DARK_GRAY, Color.GRAY,
		Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA,   Color.ORANGE,
		Color.PINK,  Color.RED,        Color.WHITE,     Color.YELLOW};

	private final String[] colStr = {"Black",
		"Blue",   "Cyan",        "Dark Grey",  "Grey",
		"Green",  "Light Grey",  "Magenta",    "Orange",
		"Pink",   "Red",         "White",      "Yellow"};

	private final JComboBox shapes = new JComboBox(S.values());
	private final JComboBox colors = new JComboBox(colStr);
	private final JButton colorPic = new JButton("Change Colour");
	private Color color = Color.RED;
	private final ShapePanel board = new ShapePanel((S)shapes.getItemAt(0), color);

	public ShapeFrame() {
		super("Shapes");
		final JPanel tmp = new JPanel();
		tmp.add(shapes);
		tmp.add(colorPic);
		add(tmp, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);

		shapes.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent ie) {
				if (ie.getStateChange() == ItemEvent.SELECTED)
					updateBoard();
			}});

		colorPic.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ie) {
				color = JColorChooser.showDialog(
					ShapeFrame.this, "Choose a colour", color);
				if (color == null)
					color = Color.RED;
				updateBoard();
			}});
	}
	
	public void updateBoard() {
		board.setShape((S)shapes.getSelectedItem(), color);
	}
}
