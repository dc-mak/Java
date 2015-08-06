package uk.ac.cam.dcm41.tick3;

public class Pattern {
	private String name;
	private String author;
	private int width;
	private int height;
	private int startCol;
	private int startRow;
	private String cells;

	//Getter methods.
	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}	

	public int getStartCol() {
		return startCol;
	}

	public int getStartRow() {
		return startRow;
	}

	public String  getCells() {
		return cells;
	}

	//Constructor: .split returns an array so you need to create a new one to take it.
	public Pattern(String format) {
		String[] inputStringArray = format.split(":");

		name = inputStringArray[0];
		author = inputStringArray[1];
		width = Integer.parseInt(inputStringArray[2]);
		height = Integer.parseInt(inputStringArray[3]);
		startCol = Integer.parseInt(inputStringArray[4]);
		startRow = Integer.parseInt(inputStringArray[5]);
		cells = inputStringArray[6];
	}

	public void initialise(boolean[][] world) {
		//Split last element in the input array into an array of rows.
		//Don't know how many rows are given.
		String[] rowsStringArray = cells.split(" ");
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

		//Chars are primitives, "strings" are not, so convert then == to compare.
		//More elegant than parsing as an int and then comparing to 1.
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNumIn[row]; col++) {
				world[row+startRow][col+startCol]
						= (cellsCharArray2D[row][col] == "1".charAt(0)) ? true : false;
			}
		}
	}
}
