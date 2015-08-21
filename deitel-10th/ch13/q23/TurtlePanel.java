// 13.23: Turtle graphics.

import java.util.Arrays;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class TurtlePanel extends JPanel {
	private static final int DIM = 40;
	private static final Color pathCol = Color.GREEN;
	private static final Color posCol = Color.RED;

	private enum Arr { UP, DOWN, LEFT, RIGHT }
	private Arr dir  = Arr.RIGHT;

	private int rowPos = 0;
	private int colPos = 0;
	private boolean penDown = false;
	private boolean[][] board = new boolean[DIM][DIM];

	public boolean penIsDown() { return penDown; }

	public TurtlePanel() {
		for (boolean[] row : board)
			Arrays.fill(row, false);
		setBackground(Color.WHITE);
	}


	public void putPenUpDown() { penDown = !penDown; }

	public void moveForward(int amt) {
		if (penDown)
			board[rowPos][colPos] = true;
		for (int i = 0; i < amt; i++) {
			switch (dir) {
				case UP   : if (rowPos > 0)     rowPos--; break;
				case DOWN : if (rowPos < DIM-1) rowPos++; break;
				case LEFT : if (colPos > 0)     colPos--; break;
				case RIGHT: if (colPos < DIM-1) colPos++;
			}
			if (penDown)
				board[rowPos][colPos] = true;
		}
		repaint();
	}

	public void turnRight() { 
		switch (dir) {
			case    UP:  dir = Arr.RIGHT; return ;
			case RIGHT:  dir = Arr.DOWN;  return ;
			case  DOWN:  dir = Arr.LEFT;  return ;
			case  LEFT:  dir = Arr.UP;
		}
		repaint();
	}

	public void turnLeft() {
		switch (dir) {
			case    UP: dir = Arr.LEFT;  return ;
			case  LEFT: dir = Arr.DOWN;  return ;
			case  DOWN: dir = Arr.RIGHT; return ;
			case RIGHT: dir = Arr.UP; 
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;

		final double x = (double) getWidth()/DIM;
		final double y = (double) getHeight()/DIM;

		for (int row = 0; row < DIM; row++)
			for (int col = 0; col < DIM; col++)
				if (row == rowPos && col == colPos) {
					g2d.setColor(posCol);
					g2d.fill(new Rectangle2D.Double(col*x, row*y, x, y));
				} else if (board[row][col]) {
					g2d.setColor(pathCol);
					g2d.fill(new Rectangle2D.Double(col*x, row*y, x, y));
				}
	}

}
