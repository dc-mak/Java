// Ex 18.20: Traversing a maze.

import java.util.Random;
public final class MazeTraverse {
	private static final Random rand = new Random();

	private final char[][] maze;

	private static final char WALL = '#';
	private static final char DEAD = '-';
	private static final char ROAD = '.';
	private static final char PATH = '/';

	private final int H;
	private final int W;

	private int endR;
	private int endC;

	private final boolean onBoard (int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}

	private final boolean visitable (int r, int c) {
		return onBoard(r,c) && maze[r][c] == ROAD;
	}

	private final boolean finished (int r, int c) {
		return r == endR && c == endC;
	}

	public MazeTraverse(int W, int H, int endR, int endC, String[] mazeStr) {
		this.W = W;
		this.H = H;

		this.endR = endR;
		this.endC = endC;

		maze = new char[H][W];
		for (int r = 0; r < H; r++)
			for (int c = 0; c < W; c++)
				maze[r][c] = mazeStr[r].charAt(c);
	}

	public boolean mazeTraversal(int row, int col) {
		if (!visitable(row,col))
			return false;

		maze[row][col] = PATH;

		if (finished(row,col))
			return true;

		if (mazeTraversal(row-1, col)) // up
			return true;
		if (mazeTraversal(row, col+1)) // right
			return true;
		if (mazeTraversal(row+1, col)) // down
			return true;
		if (mazeTraversal(row, col-1)) // left
			return true;

		maze[row][col] = DEAD;
		return false;
	}

	// TODO: en.wikipedia.org/wiki/Maze_generation_algorithm
	// This seems interesting to do properly.
	private void mazeGenerator() { }

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder();
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++)
				result.append(maze[r][c]+(c == W-1 ? "" : " "));
			result.append(r == H-1 ? "" : "\n");
		}
		return result.toString();
	}

	public static void main(String[] args){
		final String[] mazeStr = {"############",
								  "#...#......#",
								  "..#.#.####.#",
								  "###.#....#.#",
								  "#....###.#..",
								  "####.#.#.#.#",
								  "#..#.#.#.#.#",
								  "##.#.#.#.#.#",
								  "#........#.#",
								  "######.###.#",
								  "#......#...#",
								  "############"};

		MazeTraverse m = new MazeTraverse(12,12,4,11, mazeStr);
		System.out.println("Maze: \n"+m);
		System.out.println("\nSolved: "+m.mazeTraversal(2,0));
		System.out.println(m);
	}
}
