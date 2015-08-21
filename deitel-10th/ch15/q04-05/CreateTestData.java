// Ex 15.4: Creating sample test data for the app.

import java.util.Scanner;

public final class CreateTestData {
	public static void main(String[] args) {
		writeToFile trans = new writeToFile(str.OLDMAST, str.OLDMAST_PROMPT) {
			@Override
			public String getInput(Scanner input) {
				return String.format("%d %s %s %.2f%n",
					input.nextInt(), input.next(), input.next(), input.nextDouble());
			}};

		trans.addRecords();
		trans.closeFile();

		writeToFile oldmast = new writeToFile(str.TRANS, str.TRANS_PROMPT) {
			@Override
			public String getInput(Scanner input) {
				return String.format("%d %.2f%n", input.nextInt(), input.nextDouble());
			}};

		oldmast.addRecords();
		oldmast.closeFile();
	}
}
