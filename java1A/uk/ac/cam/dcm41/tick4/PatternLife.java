package uk.ac.cam.dcm41.tick4;

public class PatternLife {
	public static boolean getCell(boolean[][] world, int col, int row) {
		if ((0<=row)&&(row<=world.length-1)&&(0<=col)&&(col<=world[row].length-1)) {
			return world[row][col];
		}
		else return false;
	}
	

	public static void setCell(boolean[][] world, int col, int row, boolean value) {
		if ((0<=row)&&(row<=world.length-1)&&(0<=col)&&(col<=world[row].length-1)) {
			world[row][col] = value;
		}
	}

	public static int countNeighbours(boolean[][] world, int col, int row) {
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

	public static boolean computeCell(boolean[][] world, int col, int row) {
		
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

	public static boolean[][]  nextGeneration(boolean[][] world) {
		boolean[][] nextWorld = new boolean[world.length][world[0].length]; 
		for (int row = 0; row <= world.length - 1; row++) {
			for (int col = 0; col <= world[row].length - 1; col++) {
				setCell(nextWorld, col, row, computeCell(world, col, row));
			}
		}
		return nextWorld;
	}

	public static void print(boolean[][] world) {
		System.out.println("-");
		for (int row = 0; row <= world.length - 1; row++) {
			for (int col = 0; col <= world[row].length - 1; col++) {
				System.out.print(getCell(world, col, row) ? "#" : "_");
			}
			System.out.println();
		}
	}

	public static void play(boolean[][] world) throws Exception {
		int userResponse = 0;
			while (userResponse != 'q') {
				print(world);
				userResponse = System.in.read();
				world=nextGeneration(world);
			}
		}
	
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new PatternFormatException("\nError: no argument supplied.");
		}
		Pattern p = new Pattern(args[0]);
		boolean[][] world = new boolean[p.getHeight()][p.getWidth()];
		p.initialise(world);
		play(world);
		}
}
