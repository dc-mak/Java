// Ex 7.40: Questionnaire.

import java.util.Scanner;
import java.util.Arrays;

public class Questionnaire {
	private static final Scanner input  = new Scanner(System.in);
	private static final int people = 5;
	private static final int rating = 10;
	private static final int topics = 5;
	public static final int[][] responses = new int[topics][rating];
	public static final String[] topic = {"Topic 1 ",
											"Topic 2 ",
											"Topic 3 ",
											"Topic 4 ",
											"Topic 5 "}; 

	public static void main(String[] args){
		for (int t = 0; t < 5; t++)
			Arrays.fill(responses[t], 0);

		System.out.println("Welcome to the survey!");

		for (int p = 0; p < people; p++)  {
			System.out.println("Hello person "+p+"!");
			for (int t = 0; t < topics; t++) {
				System.out.print("Rate impotance of "+topic[t]+": ");
				responses[t][getPos()]++;
			}
		}

		// Ratings
		System.out.println("\t");
		for (int i = 1; i <= 10; i++)
			System.out.printf("%4d", i);
		System.out.print("Avg.");
		System.out.println();

		// Table
		int max_topic       = -1;
		int max_topic_total = -1;
		int min_topic       = topics-1;
		int min_topic_total = rating*rating;

		for (int t = 0; t < topics; t++) {
			int total = 0;
			System.out.print(topic[t]);
			for (int r = 0; r < rating; r++) {
				total += responses[t][r]*r;
				System.out.printf("%4d", responses[t][r]);
			}

			if (total > max_topic_total) {
				max_topic_total = total;
				max_topic = t;
			} else if (total < min_topic_total) {
				min_topic_total = total;
				min_topic = t;
			}

			System.out.printf("%.1f%n", (double) total / rating);
		}

		System.out.println("Maximum rated topic: "+topic[max_topic]);
		System.out.println("Minimum rated topic: "+topic[min_topic]);
	}

	private static int getPos() {
		int n = input.nextInt();
		while (n < 1 || n > 10) {
			System.out.print("Rating between 1 and 10 only: ");
			n = input.nextInt();
		}
		return n;
	}
}
