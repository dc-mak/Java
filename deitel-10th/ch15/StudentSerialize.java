// Ex 15.6: Serialize object student.

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class StudentSerialize {
	public static ObjectOutputStream output;
	public static Scanner input;

	public static void main(String[] args){
		openFile();
		addRecords();
		closeFile();
	}

	public static void openFile() {
		try {
			output = new ObjectOutputStream(
							Files.newOutputStream(Paths.get("students.ser")));
			input = new Scanner(Paths.get("students.txt"));
		} catch (IOException e) {
			System.err.println("Error opening file. Terminating.");
			System.exit(1);
		}
	}

	public static void addRecords() {
		int line = 1;
		while (input.hasNext()) {
			try {
				output.writeObject(new Student(
					input.nextInt(), input.next(), input.next(),
					input.nextDouble(), input.nextDouble(), input.nextDouble()));
			} catch (NoSuchElementException e) {
				System.err.println("Invalid input on line "+line+".");
				input.nextLine();
			} catch (IOException e) {
				System.err.println("Error writing to file. Terminating.");
				break;
			}
			line++;
		}
	}

	public static void closeFile() {
		try {
			if (output != null) output.close();
		} catch (IOException e) {
			System.err.println("Error closing file. Terminating.");
		}
	}
}
