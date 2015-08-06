package uk.ac.cam.dcm41.tick6star;

import java.util.ArrayList;
import java.util.Collections;

public class GraphData {

	private final int STATS_LIM = 100;

	private int xMinGen = 1;
	private ArrayList<Double> plotData = new ArrayList<Double>();

	public int getxMinGen(){ return xMinGen; }
	public int getStatsLimit() { return STATS_LIM; }

	public ArrayList<Double> getPlotData() { return plotData; }

	public int getDataSize() { return plotData.size(); };
	public double getDataAt(int i) { return plotData.get(i); };

	public double getMaxValue() { return Collections.max(plotData); };
	public double getMinValue() { return Collections.min(plotData); };
	 
	public void addPoint(double d) {
		plotData.add(d);
		if (plotData.size() > STATS_LIM) {
			plotData.remove(0);
			xMinGen += 1;
		}
	}
}
