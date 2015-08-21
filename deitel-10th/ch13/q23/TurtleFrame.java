// Ex 13.23: Turtle Graphics

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class TurtleFrame extends JFrame {
	private final JButton penUpDown = new JButton("Pen Down");
	private final JButton right = new JButton("Right");
	private final JButton left  = new JButton("Left");
	private final JButton move  = new JButton("Move Forward");

	public TurtleFrame() {
		super("Turtle Graphics");
		setLayout(new BorderLayout());
		final JPanel controls = new JPanel(new GridLayout(1, 4, 5, 5));
		controls.add(penUpDown);
		controls.add(right);
		controls.add(left);
		controls.add(move);
		final TurtlePanel board = new TurtlePanel();
		add(controls, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);

		penUpDown.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				board.putPenUpDown();
				if (board.penIsDown())
					penUpDown.setText("Pen Up");
				else
					penUpDown.setText("Pen Down");
			}});

		right.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				board.turnRight();
			}});

		left.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				board.turnLeft();
			}});

		move.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				board.moveForward(1);
			}});
	}
}
