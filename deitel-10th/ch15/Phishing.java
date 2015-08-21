// Ex 15.10: Spot phishing emails.

import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Phishing {

	public static String[] words = 
		{"win", "instant", "credit card", "income", "amaz", "natural",
		"congratulations", "billion", "dirt", "cash", "viagra", "drastic",
		"financ", "trial", "fast", "guarantee", "free", "membership",
		"secret", "trick", "growth", "dirt", "sex", "prince",
		"help", "phone", "partner", "fat", "profit", "invest"};

	public static int[] scores = {0, 2, 4, 2, 4, 4, 3, 2, 5, 5,
								6, 2, 2, 2, 3, 4, 4, 5, 5, 5,
								5, 5, 5, 6, 4, 4, 2, 5, 5, 5};

	public static int score = 0;

	public static void main(String[] args){
		if (args.length < 1)
			return;
		
		try (Scanner input = new Scanner(Paths.get(args[0]))) {
			String text = input.useDelimiter("\\A").next().toLowerCase(); // or...
			Matcher textWords = Pattern.compile("\\w+").matcher(text);

			double size = 0;
			while (textWords.find())
				size++;
			
			// overflow, density vs length, etc. but I'm tired
			for (int i = 0; i < words.length; i++) {
				Matcher mat = Pattern.compile(words[i]+"\\w*").matcher(text);
				while (mat.find())
					score += scores[i];
			}

			System.out.printf("File has a score of %.2f/10.%n", 10*size/score);

		} catch (NoSuchElementException e) {
			// with one-line, this means empty file
			System.out.println("File has a score of 0.");
		} catch (IOException e) {
			System.err.println("Error opening file. Terminating.");
		}
	}
}
// ... you can do this
// StringBuilder text = new StringBuilder();
// while (input.hasNext())
//	   text.append(input.next());
