package uk.ac.cam.dcm41.tick6star;

import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import uk.ac.cam.acr31.life.World;
import java.io.OutputStreamWriter;

public class TextLife {

	public static void main(String[] args) {
		try {
			List<Pattern> list;
			CommandLineOptions c = new CommandLineOptions(args);
			if (c.getSource().startsWith("http://"))
				list = PatternLoader.loadFromURL(c.getSource());
			else
				list = PatternLoader.loadFromDisk(c.getSource());
			if (c.getIndex() == null) {
				int i = 0;
				for (Pattern p : list)
					System.out.println((i++)+" "+p.getName()+" "+p.getAuthor());
			} else { //Assume 2 or 3 arguments
				Pattern p = list.get(c.getIndex());
				World w = null;
				if (c.getWorldType().equals(CommandLineOptions.WORLD_TYPE_AGING)) {
					w = new AgingWorld(p.getWidth(), p.getHeight());
				} else if (c.getWorldType().equals(CommandLineOptions.WORLD_TYPE_ARRAY)) {
					w = new ArrayWorld(p.getWidth(), p.getHeight());
				} else { //c.getWorldType().equals(CommandLineOption.WORLD_TYPE_LONG); 
					w = new PackedWorld();
				}
				p.initialise(w);
				int userResponse = 0;
				while (userResponse != 'q') {
					w.print(new OutputStreamWriter(System.out));
					try {
						userResponse = System.in.read();
					} catch (IOException e) {}
					w = w.nextGeneration(0);
				}
			}
		} catch (CommandLineException cfe) {
			System.out.println(cfe.getMessage());
			return;
		} catch (IOException ioe) {
			System.out.println("Error: file handling malfunction");
			return;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Error: Index out of bounds");
			return;
		} catch (PatternFormatException pfe) {
			System.out.print(pfe.getMessage());
			return;
		}
	}
}
