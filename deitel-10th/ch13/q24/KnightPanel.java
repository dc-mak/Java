// Ex 13.24: GUI version of Knight's Tour.
	
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;


public final class KnightPanel extends JPanel implements ActionListener {
	private static final int DIM = 8;
	private static final int SIZE = DIM*DIM;

	private static boolean inRange (int r, int c) {
		return 0 <= r && r < DIM && 0 <= c && c < DIM;
	}

	private static final int[] horz = { 2,  1, -1, -2, -2, -1,  1,  2};
	private static final int[] vert = {-1, -2, -2, -1,  1,  2,  2,  1};

	private static Font font = new Font("Sans Serif", Font.BOLD, 24);

	private static final int[][] initAccs = {{2,3,4,4,4,4,3,2},
											 {3,4,6,6,6,6,4,3},
											 {4,6,8,8,8,8,6,4},
											 {4,6,8,8,8,8,6,4},
											 {4,6,8,8,8,8,6,4},
											 {4,6,8,8,8,8,6,4},
											 {3,4,6,6,6,6,4,3},
											 {2,3,4,4,4,4,3,2}};

	private final int[][] board    = new int[DIM][DIM];
	private final int[][] access   = new int[DIM][DIM];
	private final JLabel status;
	private final Timer timer = new Timer(200, this);

	private int row = 0;
	private int col = 0;

	private int startPos  = SIZE; // DO NOT *TOUCH* OUTSIDE OF nextStep!
	private int loopPlace = SIZE; // DO NOT *TOUCH* OUTSIDE OF nextStep!
	private int completed = 0;    // DO NOT *TOUCH* OUTSIDE OF nextStep!

	public KnightPanel(JLabel status) {
		this.status = status;
		timer.start();
	}

	public void updateStatus() {
		status.setText(
			"Start: ("+(startPos / DIM)+", "+(startPos % DIM)+")"+
			"    Move : "+loopPlace+"." +
			(loopPlace == SIZE ? "DONE" :
				(loopPlace == startPos ? "\tClosed!" : "")));
	}

	public void nextStep() {
		// Outer "loop": resetting *whole* simulation.
		if (startPos >= SIZE-1 && loopPlace >= SIZE) {
			row = 0;
			col = 0;
			startPos  = -1;
			completed = 0;
			nextStep();
			return;
		}

		// Board "loop": next starting position for tour.
		if (loopPlace >= SIZE) {
			loopPlace = 1;
			startPos++;
			int c = startPos % DIM;
			startTourAt(startPos / DIM, c);
			nextStep();
			return;
		}

		// Tour "loop": move knight to next position.
		int move = scoreMoves();
		if (move == DIM) {
			loopPlace = SIZE;
			return;
		}

		updateAccess(-1);
		row += vert[move];
		col += horz[move];
		board[row][col] = ++loopPlace;
	}

	private void startTourAt(int r, int c) {
		for (int row = 0; row < DIM; row++)
			for (int col = 0; col < DIM; col++) {
				board[row][col]  = 0;
				access[row][col] = initAccs[row][col];
			}

		row = r;
		col = c;
		board[r][c] = 1;
	}

	private boolean notVisited(int r, int c) {
		// pre: inRange(r,c)
		return board[r][c] == 0;
	}


	private int scoreMoves() {
		int res_move  = DIM;
		int min_score = DIM+1;

		for (int move = 0; move < DIM; move++) {

			int r = row + vert[move];
			int c = col + horz[move];

			if (inRange(r,c) && notVisited(r,c))
				if (access[r][c] < min_score) {
					min_score = access[r][c];
					res_move = move;
				} else if (access[r][c] == min_score) {
					int res_next = lookAhead(res_move);
					int mov_next = lookAhead(move);
					res_move = mov_next < res_next ? move : res_move;
				}
		}
		return res_move;
	}

	private int lookAhead(int mv) {
		// Update board
		updateAccess(-1);
		row += vert[mv];
		col += horz[mv];
		board[row][col]++;

		// Find min for this move
		int min_score = DIM+1;

		for (int move = 0; move < DIM; move++) {
			int r = row + vert[move];
			int c = col + horz[move];

			if (inRange(r,c) && notVisited(r,c)
					&& access[r][c] < min_score)
				min_score = access[r][c];
		}

		// Undo all updates
		board[row][col]--;
		col -= horz[mv];
		row -= vert[mv];
		updateAccess(1);

		return min_score;
	}

	private void updateAccess(int inc) {
		for (int move = 0; move < DIM; move++) {
			int r = row + vert[move];
			int c = col + horz[move];
			if (inRange(r,c))
				access[r][c] += inc;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		nextStep();
		updateStatus();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		final int W = getWidth();
		final int H = getHeight();
		g.clearRect(0,0,W,H);

		final double X = (double) W/DIM;
		final double Y = (double) H/DIM;

		for (int i = 0; i <= DIM; i++) {
			g2d.draw(new Line2D.Double(i*X, 0, i*X, H));
			g2d.draw(new Line2D.Double(0, i*Y, W, i*Y));
		}

		g2d.setFont(font);
		for (int row = 0; row < DIM; row++)
			for (int col = 0; col < DIM; col++)
				g2d.drawString(
					Integer.toString(board[row][col]),
					(float) ((col+0.25)*X),
					(float) ((row+0.75)*Y));
			
	}
}
