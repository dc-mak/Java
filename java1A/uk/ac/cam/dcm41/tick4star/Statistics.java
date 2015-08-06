package uk.ac.cam.dcm41.tick4star;

import java.util.Arrays;
public class Statistics {
	private double MaximumGrowthRate;
	private double MaximumDeathRate;
	private int LoopStart;
	private int LoopEnd;
	private int MaximumPopulation;

	private String PatternName;

	private final int LOOP_LIMIT = 2000; //TODO Adjust limit to accomodate test results.

	public String getPatternName() {
		return PatternName;
	}	

	public void setPatternName(String Name) {
		PatternName = Name;	
	}

	public double getMaximumGrowthRate() {
		return MaximumGrowthRate;	
	}

	public double getMaximumDeathRate() {
		return MaximumDeathRate;
	}

	public int getLoopStart() {
		return LoopStart;
	}

	public int getLoopEnd() {
		return LoopEnd;
	}

	public int getMaximumPopulation() {
		return MaximumPopulation;
	}

	 public boolean[][][] findLoop(boolean[][] InitialWorld) {
		int height = InitialWorld.length;
		int width = InitialWorld[0].length;
		boolean[][][] TempWorld = new boolean[LOOP_LIMIT][height][width];

		TempWorld[0] = InitialWorld;
		TempWorld[1] = StatisticsLife.nextGeneration(TempWorld[0]);
		boolean found = false;

		//Go through all generations, comparing current with 0 to current-1.
		for (int current = 1; ((current < LOOP_LIMIT)&&(found==false)); current++) {
			for (int old = 0; ((old < current)&&(found==false)); old++) {
				if (Arrays.deepEquals(TempWorld[old], TempWorld[current])) {
					found = true;
					LoopStart = old;
					LoopEnd = current-1;
				}
			}
			if (current < LOOP_LIMIT-1) { //So TempWorld[LOOP_LIMIT] isn't called.
				TempWorld[current+1] = StatisticsLife.nextGeneration(TempWorld[current]);
			}
		}

		//If no cycless are found, cycle size should be 0.
		if (found == false) { 
			System.out.println("Pattern: "+PatternName+" takes more than " +LOOP_LIMIT+" generations to start looping.");
			LoopStart = LOOP_LIMIT;		//So that cycle size = 0.
			LoopEnd = LOOP_LIMIT;
		}

		//Now, to return an array of only the size needed.
		//Copy, so that extra generations aren't stored.
		boolean[][][] World = new boolean[LoopEnd][height][width];
		if (LoopEnd != LOOP_LIMIT) {
			World = Arrays.copyOf(TempWorld, LoopEnd+1); // +1 so that all arrays are copied.
		} else {		
			World = Arrays.copyOf(TempWorld, LoopEnd); // So that no blank arrays occur
			}										   // to avoid affecting death rates.
		return World;					 
	 }
	 
	 public int countAlive(boolean[][] world) {
		 int alive = 0;
		 for (int row = 0; row < world.length; row++) {
			 for (int col = 0; col < world[0].length; col++) {
				if (world[row][col] == true) {
					alive+=1;
				}
			 }
		 }
		 return alive;
	 }

	public void findAllRates(boolean[][][] World) {
		int[] Population = new int[World.length];
		double[] GrowthRates = new double[World.length];
		double[] DeathRates = new double[World.length]; 
		Population[0] = countAlive(World[0]);

		for (int i = 1; i < World.length; i++) {
			Population[i] = countAlive(World[i]);
			GrowthRates[i] = ((double)Population[i]-(double)Population[i-1])/((double)Population[i-1]);
			DeathRates[i] = ((double)Population[i]-(double)Population[i-1])/((double)Population[i]);
		 }
		 findMaxPopulation(Population);
		 findMaxGrowthRate(GrowthRates); 
		 findMaxDeathRate(DeathRates);
	 }

	 public void findMaxGrowthRate(double[] GrowthRates) {
		 MaximumGrowthRate = 0.0;
		 for (int i = 0; i < GrowthRates.length; i++) {
			 if (MaximumGrowthRate < GrowthRates[i]) {
				 MaximumGrowthRate = GrowthRates[i];
			 }
		 }
	 }

	 public void findMaxDeathRate(double[] DeathRates) {
		 MaximumDeathRate = 0.0;
		 for (int i = 0; i < DeathRates.length; i++) {
			 if (MaximumDeathRate < DeathRates[i]) {
				 MaximumDeathRate = DeathRates[i];
			 }
		 }
	 }

	 public void findMaxPopulation(int[] Population) {
		 MaximumPopulation = 0;
		 for (int i = 0; i < Population.length; i++) {
			 if (MaximumPopulation < Population[i]) {
				 MaximumPopulation = Population[i];
			 }
		 }
	 }

	 public Statistics(boolean[][] world, String Name) {
		 boolean[][][] World;
		 PatternName = Name;
		 World = findLoop(world);
		 findAllRates(World);
	 }
}
