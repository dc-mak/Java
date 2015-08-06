package uk.ac.cam.dcm41.tick5;

import java.awt.Color;
import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public abstract class WorldImpl implements World {

	private int width;
	private int height;
	private int generation;

	protected WorldImpl(int width, int height) {
		this.width = width;
		this.height = height;
		this.generation = 0;
	}

	protected WorldImpl(WorldImpl prev) {
		this.width = prev.width;
		this.height = prev.height;
		this.generation = prev.generation + 1;
	}

	public int getWidth() { return this.width; }

	public int getHeight() { return this.height; }

	public int getGeneration() { return this.generation; }

	public int getPopulation() { return 0; }

	protected String getCellAsString(int col,int row) {
		return getCell(col,row) ? "#" : "_";
	}

	protected Color getCellAsColour(int col,int row) {
		return getCell(col,row) ? Color.BLACK : Color.WHITE;
	}

	public void draw(Graphics g,int width, int height) {
		int worldWidth = getWidth();
		int worldHeight = getHeight();

		double colScale = (double)width/(double)worldWidth;
		double rowScale = (double)height/(double)worldHeight;

		for(int col=0; col<worldWidth; ++col) {
			for(int row=0; row<worldHeight; ++row) {
				int colPos = (int)(col*colScale);
				int rowPos = (int)(row*rowScale);
				int nextCol = (int)((col+1)*colScale);
				int nextRow = (int)((row+1)*rowScale);

				if (g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)) {
					g.setColor(getCellAsColour(col, row));
					g.fillRect(colPos,rowPos,nextCol-colPos,nextRow-rowPos);
				}
			}
		}
	}

	public WorldImpl nextGeneration(int log2StepSize) {
		WorldImpl world = this;
		int stepSize = 1<<log2StepSize;
		for(int i = 0; i < stepSize; i++) {
			world = world.nextGeneration();
		}
		return world;
	}

	public void print(Writer w) {
		PrintWriter pw = new PrintWriter(w);
		pw.println("-");
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				pw.print(getCellAsString(col, row));
			}
			pw.println();
		}
		pw.flush();
	
		//Use getCellAsString to get text representation of the cell
	}

	protected int countNeighbours(int col, int row) {
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

	protected boolean computeCell(int col, int row) {
		boolean liveCell = getCell(col, row);
		int neighbours = countNeighbours(col, row);

		boolean nextCell = false;
		//Simplifying logic to only include minterms (conditions which resulst in true).
		if ((neighbours == 3)||(liveCell&&(neighbours==2))) nextCell = true;

		return nextCell;
	}

	protected boolean inBounds (int col, int row) {
		if ((-1<row)&&(row<this.height)&&
			(-1<col)&&(col<this.width)) return true;
		else return false;
	}

	// Will be implemented by child class. Return true if cell (col,row) is alive.
	public abstract boolean getCell(int col, int row);

	// Will be implemented by child class. Set a cell to be live or dead.
	public abstract void setCell(int col, int row, boolean alive);

	// Will be implemented by child class. Step forward one generation.
	protected abstract WorldImpl nextGeneration();
}
