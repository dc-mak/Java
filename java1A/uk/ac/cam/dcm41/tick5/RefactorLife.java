package uk.ac.cam.dcm41.tick5;

import java.util.List;
import java.util.LinkedList;
import java.io.IOException;
import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class RefactorLife {
	public static void play(World world) throws Exception {
		int userResponse = 0;
		WorldViewer viewer = new WorldViewer();
		while (userResponse != 'q') {
			Writer w = new OutputStreamWriter(System.out);
			world.print(w);
			viewer.show(world);
			userResponse = System.in.read();
			world=world.nextGeneration(0);
		}
		viewer.close();
	}
	
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Error: no argument supplied");
			return;
		}

		//For the least amount of code editing. Literally had to replace three array
		//indexes and now this whole thing is much more scalable to adding more
		//arguments, because the order can be rearranged at will.
		int sourceArg = 0;
		int patArg = 1;
		String worldType = "--array";
		if (args.length == 3) {
			worldType = args[0];
			sourceArg = 1;
			patArg = 2;
		}

		List<Pattern> InputPatternsList = new LinkedList<Pattern>();
		try { //Try block outside of ifs because code needs to be executed regardless of the number of arguments.
			if (args[sourceArg].startsWith("http://")) {
				InputPatternsList = PatternLoader.loadFromURL(args[sourceArg]);
			} else {
				InputPatternsList = PatternLoader.loadFromDisk(args[sourceArg]);
			}
		} catch (IOException ioe) {
			System.out.println("Error: file handling malfunction");
			return;
		}
		if (args.length == 1) {
			for (Pattern i: InputPatternsList) {
				i.printAll();
			}
		} else { //2 or more arguments
			try {
				if (InputPatternsList.size() == 0) {
					System.out.println("Error: no valid patterns on any line");
					return;
				} else {
					Pattern p = InputPatternsList.get(Integer.parseInt(args[patArg]));
					World world = null;
					if (worldType.equals("--array")) {
						world = new ArrayWorld(p.getWidth(), p.getHeight());
					} else if (worldType.equals("--long")) {
						world = new PackedWorld();
					} else if (worldType.equals("--aging")) {
						world = new AgingWorld(p.getWidth(), p.getHeight());
					} else {
						System.out.println("Implementation argument is optional (default '--array'). Must be either '--array' or '--long'.");
						return;
					}
					p.initialise(world);
					play(world);
				}
			} catch (NumberFormatException nfe) { //E.g. for .parseInt("a").
				System.out.println("Error: second argument must be a valid line number in the file");
				return;
			} catch (IndexOutOfBoundsException iofb) {
				System.out.println("Error: second argument must refer to a line number in file\nCheck that all patterns are valid if line number is because there are only "+(InputPatternsList.size())+" valid pattern(s) in the file");
				return;
			}
		}
	}
}
