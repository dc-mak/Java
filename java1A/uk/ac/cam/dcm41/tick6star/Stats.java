package uk.ac.cam.dcm41.tick6star;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import uk.ac.cam.acr31.life.World;

public class Stats {
	
	private GraphData Pop = new GraphData();
	private GraphData PopChange = new GraphData();
	private GraphData GrowthDeath = new GraphData();

	public GraphData getPop() { return Pop; }
	public GraphData getPopChange() { return PopChange; }
	public GraphData getGrowthDeath() { return GrowthDeath; }

	public void update(World w) {
		Pop.addPoint((double)w.getPopulation());

		int index = Pop.getDataSize()-1;
		if (index > 0) {
			double change = Pop.getDataAt(index) - Pop.getDataAt(index-1);
			PopChange.addPoint(change);

			double rate = change/Pop.getDataAt(index-1);
			GrowthDeath.addPoint(change);
		}
	}
}
