// Ex 5.31: A dummy quiz.

import java.util.Scanner;

public class Quiz {
	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);

		String web1 = "Website 1";
		String web2 = "Website 2";
		String web3 = "Website 3";

		String q1   = "Question 1";
		String opt1 = "Options";
		int ans1    = 1;

		String q2   = "Question 2";
		String opt2 = "Options";
		int ans2    = 2;

		String q3   = "Question 3";
		String opt3 = "Options";
		int ans3    = 3;

		String q4   = "Question 4";
		String opt4 = "Options";
		int ans4    = 4;

		String q5   = "Question 5";
		String opt5 = "Options";
		int ans5    = 1;

		int correct = 0;
		System.out.printf("%s%n%s%nEnter answer: ", q1, opt1);
		if (getPosInt (input) == ans1)
			correct += 1;

		System.out.printf("%s%n%s%nEnter answer: ", q2, opt2);
		if (getPosInt (input) == ans2)
			correct += 1;

		System.out.printf("%s%n%s%nEnter answer: ", q3, opt3);
		if (getPosInt (input) == ans3)
			correct += 1;

		System.out.printf("%s%n%s%nEnter answer: ", q4, opt4);
		if (getPosInt (input) == ans4)
			correct += 1;

		System.out.printf("%s%n%s%nEnter answer: ", q5, opt5);
		if (getPosInt (input) == ans5)
			correct += 1;

		switch (correct) {
			case  5: System.out.println("Excellent!"); break;
			case  4: System.out.println("Very good."); break;
			default: System.out.printf ("%s%n%s%n\t%s%n\t%s%n\t%s%n",
						"Time to brush up on your knowledge of global warming.",
						"Try these websites:", web1, web2, web3);
		}

	}

	private static int getPosInt(Scanner input) {
		int inVal = input.nextInt();
		while (inVal < 1 || 4 < inVal) {
			System.out.println("Please select a valid option.");
			System.out.print("Enter answer: ");
			inVal = input.nextInt();
		}
		return inVal;
	}
}
