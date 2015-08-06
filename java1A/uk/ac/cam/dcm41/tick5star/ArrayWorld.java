package uk.ac.cam.dcm41.tick5star;

public class ArrayWorld extends WorldImpl {
	private boolean[][] cells;
	
	public ArrayWorld(int w, int h) {
		super(w, h);
		cells = new boolean[h][w];
	}
	
	protected ArrayWorld(ArrayWorld prev) {
		super(prev);
		cells = new boolean[prev.getHeight()][prev.getWidth()];
	}
	
	public boolean getCell(int col, int row) {
		if (inBounds(col, row)) return cells[row][col];
		else return false;
	}

	public void setCell(int col, int row, boolean alive) {
		if (inBounds(col, row)) cells[row][col] = alive;
	}
	
	protected WorldImpl nextGeneration() {
		WorldImpl world = new ArrayWorld(this);	
		for (int row = 0; row < this.getHeight(); row++) {
			for (int col = 0; col < this.getWidth(); col++) {
				world.setCell(col, row, computeCell(col, row));
			}
		}
		return world;
	}
}
