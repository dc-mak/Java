// Ex 15.4: I realised CreateTransaction and CreateMaster were very similar.

/* For serialized objects:
 * import java.io.ObjectOutputStream;
 * private ObjectOutputStream;
 * TransactionRecord/Account tmp = new TransactionRecord/Account(...);
 * output.writeObject(tmp);
 */

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class writeToFile {
	private final String FILENAME;
	private Formatter output;
	private final String HEADER;

	public writeToFile(String FILENAME, String HEADER) {
		this.FILENAME = FILENAME;
		this.HEADER   = HEADER;
		try {
			output = new Formatter(FILENAME);
		} catch (SecurityException e) {
			System.err.println("Write permission denied. Terminating");
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.err.println("Error writing to file. Terminating");
			System.exit(1);
		}
	}

	public abstract String getInput(Scanner input);

	public void addRecords() {
		final Scanner input = new Scanner(System.in);
		System.out.print(HEADER);

		while (input.hasNext()) {
			try {
				output.format(getInput(input));
			} catch (FormatterClosedException e) {
				System.err.println("Error writing to file. Terminating");
				break;
			} catch (NoSuchElementException e) {
				System.err.println("Invalid input. Please try again.");
				input.nextLine();
			}
			System.out.print("? ");
		}
	}

	public void closeFile() {
		if (output != null)
			output.close();
	}
}
