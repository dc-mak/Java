package uk.ac.cam.dcm41.tick5star;

import uk.ac.cam.acr31.life.World;

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

	public String getCells() {
		return cells;
	}

	//Constructor: .split returns an array so you need to create a new one to take it.
	public Pattern(String format) throws PatternFormatException  {
		try {
			String[] inputStringArray = format.split(":");

			name = inputStringArray[0];
			author = inputStringArray[1];
			width = Integer.parseInt(inputStringArray[2]);
			height = Integer.parseInt(inputStringArray[3]);
			startCol = Integer.parseInt(inputStringArray[4]);
			startRow = Integer.parseInt(inputStringArray[5]);
			cells = inputStringArray[6];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new PatternFormatException("Error: too few colon-separated values.");
		} catch (NumberFormatException e) {
			throw new PatternFormatException("Error: width, height, start column, start row and specified cells must all be numbers.");
		}
	}

	public void initialise(World InputWorld) throws PatternFormatException {
		//Split last element in the input array into an array of rows.
		//Don't know how many rows are given.
		String[] rowsStringArray = cells.split(" ");
		if (rowsStringArray.length > height - startRow) {
			throw new PatternFormatException("Error: number of rows specified in cells is greater than number of rows from Start Row to Height.");
		}
		int rowNum = rowsStringArray.length;
	
		//Declare an array of characters. Don't know how many columns in a row.
		//Can't assume square. Hence integer array: number of columns in a row. 
		int[] colNumIn = new int[rowNum];

		//Fill in the cells character array with individual *characters*.
		for (int row = 0; row < rowNum; row++) {
			if (rowsStringArray[row].length() > width - startCol) {
				throw new PatternFormatException("Error: number of columns specified in cells is greater than number of columns from Start Column to Width.");
			}
			colNumIn[row] = rowsStringArray[row].length();
		}

		//Convert string to character to get NumberFormatException in Integer.parseInt.
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNumIn[row]; col++) {
				if  (rowsStringArray[row].charAt(col) == '1') {
					InputWorld.setCell(col+startCol, row+startRow, true);
				} else if (rowsStringArray[row].charAt(col) == '0') {
					InputWorld.setCell(col+startCol, row+startRow, false);
				} else {
					throw new PatternFormatException("Error: only number 0 or 1 in cells.");
				}
			}
		}
	}

	public void printAll() {
				System.out.println(name+":"+author+":"+width+":"+height+":"+startCol+":"+startRow+":"+cells);
	}
}
