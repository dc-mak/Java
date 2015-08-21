// Ex 15.8: Analyse results of student pol in numbers.txt.

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AnalysePoll {
	public static void main(String[] args) {
		openFile();
		writeResults();
	} 

	private static final int[] freq = new int[5];

	private static void openFile() {
		Arrays.fill(freq, 0);
		try (Scanner input = new Scanner(Paths.get("numbers.txt"))) {
			while (input.hasNext())
				freq[input.nextInt()-1]++;
		} catch (NoSuchElementException | IllegalStateException | IOException e) {
			System.err.println("Error processing file. Terminating");
			System.exit(1);
		}
	}

	private static void writeResults() {
		try (Formatter output = new Formatter("output.txt")) {

			output.format("%s\t%s%n","Score", "Frequency");
			for (int i = 0; i < 5; i++)
				output.format("%d\t\t%d%n", i+1, freq[i]);

		} catch (SecurityException e) {
			System.err.println("Write permission denied. Terminating.");
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.err.println("Error opening file. Terminating.");
			System.exit(1);
		} catch (FormatterClosedException e) {
			System.err.println("Error writing to file. Terminating.");
			System.exit(1);
		}
	}
} 
