// 7.21: Turtle graphics.

import java.util.Arrays;

public class Turtle {
	private static final int BOARD_SIZE = 20;
	private boolean[][] board = new boolean[BOARD_SIZE][BOARD_SIZE];

	public Turtle() {
		for (boolean[] row : board)
			Arrays.fill(row, false);
	}

	private enum Arrow { UP, DOWN, LEFT, RIGHT }
	private Arrow dir  = Arrow.RIGHT;
	private int rowPos = 0;
	private int colPos = 0;
	private boolean penDown = false;

	public void putPenUp ()   { penDown = false; }
	public void putPenDown () { penDown = true; }

	public void moveForward(int amt) {
		if (penDown)
			board[rowPos][colPos] = true;
		for (int i = 0; i < amt; i++) {
			switch (dir) {
				case UP   : if (rowPos > 0)            rowPos --; break;
				case DOWN : if (rowPos < BOARD_SIZE-1) rowPos ++; break;
				case LEFT : if (colPos > 0)            colPos --; break;
				case RIGHT: if (colPos < BOARD_SIZE-1) colPos ++;
			}
			if (penDown)
				board[rowPos][colPos] = true;
		}
	}

	public void turnRight() { 
		switch (dir) {
			case    UP:  dir = Arrow.RIGHT; return ;
			case RIGHT:  dir = Arrow.DOWN;  return ;
			case  DOWN:  dir = Arrow.LEFT;  return ;
			case  LEFT:  dir = Arrow.UP;
		}
	}

	public void turnLeft() {
		switch (dir) {
			case    UP: dir = Arrow.LEFT;  return ;
			case  LEFT: dir = Arrow.DOWN;  return ;
			case  DOWN: dir = Arrow.RIGHT; return ;
			case RIGHT: dir = Arrow.UP; 
		}
	}

	public void showBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++)
				if (row == rowPos && col == colPos)
					System.out.print(penDown ? 'X' : 'O');
				else
					System.out.print(board[row][col] ? '#' : '-');
			System.out.println();
		}
	}
}
