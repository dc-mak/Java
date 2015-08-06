package uk.ac.cam.dcm41.tick5;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class TestArrayWorld implements World {

	private int generation;
	private int width;
	private int height;
	private boolean[][] cells;

	public TestArrayWorld(int w, int h) {
		width = w;
		height = h;
		generation = 0;
		cells = new boolean[h][w];
	}

	protected TestArrayWorld(TestArrayWorld prev) {
		width = prev.width;
		height = prev.height;
		generation = prev.generation+1;
		cells = new boolean[prev.height][prev.width];
	}

	private boolean inBounds (int col, int row) {
		if ((-1<row)&&(row<height)&&
			(-1<col)&&(col<width)) return true;
		else return false;
	}

	public boolean getCell(int col, int row) {
		if (inBounds(col, row)) return cells[row][col];
		else return false;
	}

	public void setCell(int col, int row, boolean alive) {
		if (inBounds(col, row)) cells[row][col] = alive;
	}

	public int getWidth()  { return width; }
	public int getHeight()  { return height; }
	public int getGeneration()  {return generation; }
	public int getPopulation()  { return 0; }

	public void print(Writer w) {
		PrintWriter pw = new PrintWriter(w);
		pw.println("-");
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[row].length; col++) {
				pw.print(getCell(col, row) ? "#" : "_");
			}
			pw.println();
		}
		pw.flush();
	}

	public void draw(Graphics g, int width, int height)  { /*Leave empty*/ }

	private int countNeighbours(int col, int row) {
		int total = 0;
		for (int r = row-1; r<row+2; r++) {
			for (int c = col-1; c<col+2; c++) {
				if (getCell(c, r)&&!((c == col)&&(r == row))) {
					total+=1;
				}
			}
		}
		return total;
	}

	private boolean computeCell(int col, int row) {
		boolean liveCell = getCell(col, row);
		int neighbours = countNeighbours(col, row);
		boolean nextCell = false;

		//Simplifying logic to only include minterms (conditions which resulst in true).
		if ((neighbours == 3)||(liveCell&&(neighbours==2))) nextCell = true;

		return nextCell;
	}

	private TestArrayWorld nextGeneration() {
		//Construct a new TestArrayWorld object to hold the next generation:
		TestArrayWorld world = new TestArrayWorld(this);
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				world.setCell(col, row, computeCell(col, row));
			}
		}
		return world;
	}

	public World nextGeneration(int log2StepSize)  {
		TestArrayWorld world = this;
		int stepSize = 1<<log2StepSize;
		for(int i = 0; i < stepSize; i++) {
			world = world.nextGeneration();
		}
		return world;
	}
}
