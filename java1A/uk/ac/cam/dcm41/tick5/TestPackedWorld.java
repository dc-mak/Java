package uk.ac.cam.dcm41.tick5;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class TestPackedWorld implements World {

	private int generation;
	private int width;
	private int height;
	private long cells;

	public TestPackedWorld() {
		width = 8;
		height = 8;
		generation = 0;
		cells = 0L;
	}

	protected TestPackedWorld(TestPackedWorld prev) {
		width = prev.width;
		height = prev.height;
		generation = prev.generation+1;
		cells = 0L;
	}

	private boolean inBounds (int col, int row) {
		if ((-1<row)&&(row<height)&&
			(-1<col)&&(col<width)) return true;
		else return false;
	}

	public boolean getCell(int col, int row) {
		if (inBounds(col, row)) return PackedLong.get(cells, row*8+col);
		else return false;
	}

	public void setCell(int col, int row, boolean alive) {
		if (inBounds(col, row)) {
			cells = PackedLong.set(cells, row*8+col, alive);
		}
	}

	public int getWidth()  { return width; }
	public int getHeight()  { return height; }
	public int getGeneration()  {return generation; }
	public int getPopulation()  { return 0; }

	public void print(Writer w) {
		PrintWriter pw = new PrintWriter(w);
		pw.println("-");
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
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

	private TestPackedWorld nextGeneration() {
		//Construct a new TestPackedWorld object to hold the next generation:
		TestPackedWorld world = new TestPackedWorld(this);
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				world.setCell(col, row, computeCell(col, row));
			}
		}
		return world;
	}

	public World nextGeneration(int log2StepSize)  {
		TestPackedWorld world = this;
		int stepSize = 1<<log2StepSize;
		for(int i = 0; i < stepSize; i++) {
			world = world.nextGeneration();
		}
		return world;
	}
}
