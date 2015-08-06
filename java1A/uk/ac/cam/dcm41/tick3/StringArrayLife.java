package uk.ac.cam.dcm41.tick3;

public class StringArrayLife {
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
		String formatString = args[0];

		//Split argument into an string array of inputs
		String[] inputStringArray = formatString.split(":");
		
		//Split last element in the input array into an array of rows.
		//Don't know how many rows are given.
		String[] rowsStringArray = inputStringArray[6].split(" ");
		int rowNum = rowsStringArray.length;
	
		//Declare an array of characters. Don't know how many columns in a row.
		//Can't assume square. Hence integer array: number of columns in a row. 
		char[][] cellsCharArray2D = new char[rowNum][];
		int[] colNumIn = new int[rowNum];

		//Fill in the cells character array with individual *characters*.
		for (int row = 0; row < rowNum; row++) {
			cellsCharArray2D[row] = rowsStringArray[row].toCharArray();
			colNumIn[row] = cellsCharArray2D[row].length;
		}

		int width = Integer.parseInt(inputStringArray[2]);
		int height = Integer.parseInt(inputStringArray[3]);
		int startCol = Integer.parseInt(inputStringArray[4]);
		int startRow = Integer.parseInt(inputStringArray[5]);
		boolean [][] world = new boolean [height][width];

		//Chars are primitives, "strings" are not, so convert then == to compare.
		//More elegant than parsing as an int and then comparing to 1.
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNumIn[row]; col++) {
				world[row+startRow][col+startCol]
						= (cellsCharArray2D[row][col] == "1".charAt(0)) ? true : false;
			}
		}
		play(world);
		}
}
