// Ex 15.8: Administer a survey and store the results in a file "numbers.txt".

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class TakePoll {
	public static void main(String[] args){
		try (Formatter output = new Formatter("numbers.txt")) {
			getResponses(output);
		} catch (SecurityException e) {
			System.err.println("Write permission denied. Terminating.");
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.err.println("Error opening file. Terminating.");
			System.exit(1);
		}
	}

	private static void getResponses(Formatter output) {
		final Scanner input = new Scanner(System.in);
		System.out.printf("%s%n%s%n? ",
				"Enter survery results.",
				"All responses must be digits between 1 and 5 inclusive.");
		
		while (input.hasNext()) {
			try {
				output.format("%d%n", getScore(input));
			} catch (FormatterClosedException e) {
				System.err.println("Error writing to file. Terminating.");
				break;
			} catch (NoSuchElementException e) {
				System.err.println("Invalid input. Please try again.");
				input.nextLine();
			}
			System.out.print("? ");
		}
	}

	private static int getScore(Scanner input) {
		int val = input.nextInt();
		while (val < 1 || val > 5) {
			System.out.print("Invalid input. Please try again.\n? ");
			val = input.nextInt();
		}
		return val;
	}
}
