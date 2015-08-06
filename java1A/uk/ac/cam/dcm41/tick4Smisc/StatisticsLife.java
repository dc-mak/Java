package uk.ac.cam.dcm41.tick4Smisc;

import java.util.List;
import java.util.LinkedList;
import java.io.IOException;

public class StatisticsLife {
	public static boolean getCell(boolean[][] world, int col, int row) {
		if ((0<=row)&&(row<=world.length-1)&&(0<=col)&&(col<=world[row].length-1)) {
			return world[row][col];
		}
		else return false;
	}
	
	public static void setCell(boolean[][] world, int col, int row, boolean value) {
		if ((0<=row)&&(row<=world.length-1)&&(0<=col)&&(col<=world[row].length-1)) {
			world[row][col] = value;
		}
	}

	public static int countNeighbours(boolean[][] world, int col, int row) {
		int total = 0;
		for (int row_i = row - 1; row_i <= row + 1; row_i++) {
			for (int col_i = col - 1; col_i <= col + 1; col_i++) {
				if (getCell(world, col_i, row_i) && !((col_i == col) && (row_i == row))) {
					total = total + 1;
				}
			}
		}
		return total;
	}

	public static boolean computeCell(boolean[][] world, int col, int row) {
		
		// liveCell is true is the cell at position (col,row) in world is live
		boolean liveCell = getCell(world, col, row);

		//neighbours is the number of live neigbours to cell (col, row)
		int neighbours = countNeighbours(world, col, row);

		//We will return this value at the end of the method to indicate whether
		//cell (col, row) should be live in the next generation
		boolean nextCell = false;

		//A live cell with less than two neighbours dies (underpopulation)
		if (neighbours < 2) {
			nextCell = false;
		}

		//A live cell with two or three neighbours lives (a balanced population)
		if (liveCell && ((neighbours == 2) || (neighbours == 3))) {
			nextCell = true;
		}

		//A live cell with more than three neighbours dies (overcrowding)
		if (liveCell &&  (neighbours > 3)) {
			nextCell = false;
		}

		//A dead cell with exactly three live neighbours comes alive
		if (!liveCell && (neighbours == 3)) {
			nextCell = true;
		}	
		
		return nextCell;
	}

	public static boolean[][]  nextGeneration(boolean[][] world) {
		boolean[][] nextWorld = new boolean[world.length][world[0].length]; 
		for (int row = 0; row <= world.length - 1; row++) {
			for (int col = 0; col <= world[row].length - 1; col++) {
				setCell(nextWorld, col, row, computeCell(world, col, row));
			}
		}
		return nextWorld;
	}

	public static void print(boolean[][] world) {
		System.out.println("-");
		for (int row = 0; row <= world.length - 1; row++) {
			for (int col = 0; col <= world[row].length - 1; col++) {
				System.out.print(getCell(world, col, row) ? "#" : "_");
			}
			System.out.println();
		}
	}

	public static void play(boolean[][] world) throws Exception {
		int userResponse = 0;
			while (userResponse != 'q') {
				print(world);
				userResponse = System.in.read();
				world=nextGeneration(world);
			}
		}

public static Statistics analyse(Pattern p) throws PatternFormatException {
		boolean[][] world = new boolean[p.getHeight()][p.getWidth()];
		p.initialise(world);
		Statistics s = new Statistics(world, p.getName());
		return s;
	}

	public static void PrintStats(List<Statistics> PatternStatsList) {
		int LongestStart = -1;
		int LongestCycle = -1;
		double BiggestGrowthRate = -1.0;
		double BiggestDeathRate = -1.0;
		int LargestPop = -1;

		String StrStart = new String("");
		String StrCycle = new String("");
		String StrGrowthRate = new String("");
		String StrDeathRate = new String("");
		String StrPop = new String("");

		for (Statistics s: PatternStatsList) {
			if (LongestStart < s.getLoopStart()) {
				LongestStart = s.getLoopStart();
				StrStart = s.getPatternName();
			}	
			if (LongestCycle < (s.getLoopEnd()-s.getLoopStart())) {
				LongestCycle = s.getLoopEnd()-s.getLoopStart();
				StrCycle = s.getPatternName();
			}	
			if (BiggestGrowthRate < s.getMaximumGrowthRate()) {
				BiggestGrowthRate = s.getMaximumGrowthRate();
				StrGrowthRate = s.getPatternName();
			}	
			if (BiggestDeathRate < s.getMaximumDeathRate()) {
				BiggestDeathRate = s.getMaximumDeathRate();
				StrDeathRate = s.getPatternName();
			}	
			if (LargestPop < s.getMaximumPopulation()) {
				LargestPop = s.getMaximumPopulation();
				StrPop = s.getPatternName();
			}	
		}
		System.out.println("Longest start: "+StrStart+" ("+LongestStart+")");
		System.out.println("Longest cycle: "+StrCycle+" ("+LongestCycle+")");
		System.out.println("Biggest growth rate: "+StrGrowthRate+" ("+BiggestGrowthRate+")");
		System.out.println("Biggest death rate: "+StrDeathRate+" ("+BiggestDeathRate+")");
		System.out.println("Largest population: "+StrPop+" ("+LargestPop+")");
	}

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Error: no argument supplied");
			return;
		} 
		List<Pattern> InputPatternsList = new LinkedList<Pattern>();
		List<Statistics> PatternStatsList = new LinkedList<Statistics>();
		try { //Try block outside of ifs because code needs to be executed regardless of the number of arguments.
			if (args[0].startsWith("http://")) {
				InputPatternsList = PatternLoader.loadFromURL(args[0]);
			} else {
				InputPatternsList = PatternLoader.loadFromDisk(args[0]);
			}
		} catch (IOException ioe) {
			System.out.println("Error: file handling malfunction");
			return;
		}
		if (InputPatternsList.size() == 0) {
			System.out.println("Error: no valid patterns on any line");
			return;
		} else {
			if (args.length == 1) {
				for (Pattern p: InputPatternsList) {
					System.out.println("Analysing "+p.getName());
					Statistics s = analyse(p);
					PatternStatsList.add(s);
				}
				PrintStats(PatternStatsList);
			} else { //2 or more arguments
				try {
					if (InputPatternsList.size() == 0) {
						System.out.println("Error: no valid patterns on any line");
						return;
					} else {
						Pattern p = InputPatternsList.get(Integer.parseInt(args[1]));
						boolean[][] world = new boolean[p.getHeight()][p.getWidth()];
						p.initialise(world);
						play(world);
					}
				} catch (NumberFormatException n) { //E.g. for .parseInt("a").
					System.out.println("Error: second argument must be a valid line number in the file");
					return;
				} catch (IndexOutOfBoundsException aofb) {
					System.out.println("Error: second argument must refer to a line number in file\nCheck that all patterns are valid if line number is because there are only "+(InputPatternsList.size())+" valid pattern(s) in the file");
					return;
				}
			}
		}
	}
}
