// Ex 7.22)b: Knight's tour around a chess board. 

import java.util.Arrays;

public class Knight {
	private static final int DIM = 8;
	private static final int SIZE = DIM*DIM;

	private static final int[][] board = new int[8][8];

	private static final int[] horz = { 2,  1, -1, -2, -2, -1,  1,  2};
	private static final int[] vert = {-1, -2, -2, -1,  1,  2,  2,  1};

	private static int row = 0;
	private static int col = 0;

	public static void main(String[] args) {
		int moves = 0;
		board[0][0] = 1;

		for (int i = 0; i < SIZE; i++) {
			int move = nextMove();

			if (move == DIM)
				break;

			row += vert[move];
			col += horz[move];

			moves++;
			board[row][col]++;
		}

		printBoard(moves);
	}

	private static void printBoard(int moves) {
		for (int[] row : board)
			System.out.println(Arrays.toString(row));
		System.out.println("Moves: "+moves);
	}

	private static int nextMove() {
		for (int move = 0; move < DIM; move++) {
			int r = row + vert[move];
			int c = col + horz[move];
			if (inRange(r,c) && board[r][c] == 0)
				return move;
		}
		return DIM;
	}

	private static boolean inRange(int r, int c) {
		return 0 <= r && r < DIM && 0 <= c && c < DIM;
	}

}
