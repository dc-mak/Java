package uk.ac.cam.dcm41.tick6;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.net.URL;
import java.net.URLConnection;

public class PatternLoader {

	public static List<Pattern> load(Reader r) throws IOException {
		//Read in all available patterns from the reader object
		BufferedReader buff = new BufferedReader(r);

		//To save patterns as I load them.
		List<Pattern> resultList = new LinkedList<Pattern>();
		int lineNum = 0;
		String fileLine;	//Without fileLine, buff.readLine() would be read twice, once for the null test, and then for the assignment to p, FOR EVERY DAMN LOOP.

		while ((fileLine = buff.readLine()) != null) {
			//Store all valid patterns in a List object
			try {
				//Convert the pattern strings into Pattern objects
				Pattern p = new Pattern(fileLine);
				resultList.add(p);
			} catch (PatternFormatException pfe) {
				System.out.println("\n"+pfe.getMessage()+" On line "+lineNum+".");
			} finally {
				lineNum++;
			}
		}


		//If no valid patterns, will return empty List.
		return resultList;
	}

	public static List<Pattern> loadFromURL(String url) throws IOException {
		URL destination = new URL(url);
		URLConnection conn = destination.openConnection();
		return load(new InputStreamReader(conn.getInputStream()));
	}

	public static List<Pattern> loadFromDisk(String filename) throws IOException {
		return load(new FileReader(filename));
	}
}
