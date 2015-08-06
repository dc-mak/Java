package uk.ac.cam.dcm41.tick2;

public class TinyLife {
	public static boolean getCell(long world, int col, int row) {
		return (0<=col&&col<=7) && (0<=row&&row<=7) ?
			PackedLong.get(world, row*8 + col) : false ;
	}

	public static long setCell(long world, int col, int row, boolean value) {
		return (0<=col&&col<=7) && (0<=row&&row<=7) ?
			PackedLong.set(world, row*8 + col, value) : world;
	}

	public static int countNeighbours(long world, int col, int row) {
		int total = 0;
		for (int row_i = row - 1; row_i <= row + 1; row_i++) {
			for (int col_i = col - 1; col_i <= col + 1; col_i++) {
				if (getCell(world, col_i, row_i) && !((col_i == col) && (row_i == row))) {
					total = total + 1;
				}
			}
		}
		return total;	
	}

	public static boolean computeCell(long world, int col, int row) {
		
		// liveCell is true is the cell at position (col,row) in world is live
		boolean liveCell = getCell(world, col, row);

		//neighbours is the number of live neigbours to cell (col, row)
		int neighbours = countNeighbours(world, col, row);

		//We will return this value at the end of the method to indicate whether
		//cell (col, row) should be live in the next generation
		boolean nextCell = false;

		//A live cell with less than two neighbours dies (underpopulation)
		if (neighbours < 2) {
			nextCell = false;
		}

		//A live cell with two or three neighbours lives (a balanced population)
		if (liveCell && ((neighbours == 2) || (neighbours == 3))) {
			nextCell = true;
		}

		//A live cell with more than three neighbours dies (overcrowding)
		if (liveCell &&  (neighbours > 3)) {
			nextCell = false;
		}

		//A dead cell with exactly three live neighbours comes alive
		if (!liveCell && (neighbours == 3)) {
			nextCell = true;
		}	

		return nextCell;
	}

	public static long  nextGeneration(long world) {
		long nextWorld = 0L;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				nextWorld  = setCell(nextWorld, col, row, computeCell(world, col, row));
			}
		}
		return nextWorld;
	}

	public static void print(long world) {
		System.out.println("-");
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				System.out.print(getCell(world, col, row) ? "#" : "_");
			}
			System.out.println();
		}
	}

	public static void play(long world) throws Exception {
		int userResponse = 0;
		while (userResponse != 'q') {
			print(world);
			userResponse = System.in.read();
			world=nextGeneration(world);
		}
	}

	public static void main(String[] args) throws Exception {
		play(Long.decode(args[0]));
	}
}
