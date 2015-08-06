package uk.ac.cam.dcm41.tick7star;

public class RowData {
	private final int startx;
	private final int starty;
	private final String rows;

	public int getStartx() { return startx; }
	public int getStarty() { return starty; }
	public String getRows() { return rows; }

	public RowData(int x, int y, String s) {
		startx = x; 
		starty = y; 
		rows = s; 
	}
}
