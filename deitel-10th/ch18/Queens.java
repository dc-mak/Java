// Ex 18.15: Eight Queens puzzle.
//		TODO: REDO!
//		[r1,r2,r3,r4,r5,r6,r7,r8]
//		where each array position is a column index and each element is a row
//		index and the array is simply a permutation of [1,2,3,4,5,6,7,8] that
//		satisfies r_i - i != r_j - j for all 0 <= i,j <= 7 such that i <> j.

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.List;
import java.awt.Point;

public final class Queens {
	private static final int DIM = 8;
	private static final boolean[][] board = new boolean[DIM][DIM];
	private static final List<Point> points = new ArrayList<>(DIM);

	public static void main(String[] args){
		updateBoard();
		Queen(0);

		final StringBuilder[] show = new StringBuilder[DIM];
		for (int i = 0; i < DIM; i++)
			show[i] = new StringBuilder("- - - - - - - -");

		for (Point p : points)
			show[p.y].setCharAt(2*p.x, '#');

		for (StringBuilder s : show)
			System.out.println(s);
	}

	private static boolean Queen(int col) {
		if (col == DIM)
			return true;

		for (int row = 0; row < DIM; row++) {
			if (board[row][col]) {
				Point tmp = new Point(col, row);
				points.add(tmp);
				updateBoard();
				if (Queen(col+1)) {
					return true;
				} else {
					points.remove(tmp);
					updateBoard();
				}
			}
		}
		return false;
	}

	private static void updateBoard() {
		for (boolean[] row : board)
			Arrays.fill(row, true);

		for (Point p : points) {
			Arrays.fill(board[p.y], false);

			for (int r = 0; r < DIM; r++)
				board[r][p.x] = false;

			Predicate<Integer> isIn = x -> (x >= 0 && x < DIM);
			for (int i = 1-DIM; i < DIM; i++) {
				if (isIn.test(p.y+i) && isIn.test(p.x+i))
					board[p.y+i][p.x+i] = false;
				if (isIn.test(p.y+i) && isIn.test(p.x-i))
					board[p.y+i][p.x-i] = false;
			}
		}
	}
}
