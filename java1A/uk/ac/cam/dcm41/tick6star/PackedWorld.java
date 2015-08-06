package uk.ac.cam.dcm41.tick6star;

public class PackedWorld extends WorldImpl {
	private long cells;

	public PackedWorld() {
		super(8, 8);
		cells = 0L;
	}

	protected PackedWorld (PackedWorld prev) {
		super(prev);
		cells = 0L;	
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

	protected PackedWorld nextGeneration() {
		//Construct a new TestPackedWorld object to hold the next generation:
		PackedWorld world = new PackedWorld(this);
		for (int row = 0; row < this.getHeight(); row++) {
			for (int col = 0; col < this.getWidth(); col++) {
				world.setCell(col, row, computeCell(col, row));
			}
		}
		return world;
	}
}
