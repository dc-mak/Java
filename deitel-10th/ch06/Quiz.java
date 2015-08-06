// Ex 3.5-3.9: Make an arithmetic quiz.

import java.util.Scanner;
import java.security.SecureRandom;

public class Quiz {
	private final Scanner input;
	private final SecureRandom rand;

	private Q qtype;
	private int diff;

	public Quiz(Scanner input, SecureRandom rand) {
		this.input = input;
		this.rand = rand;
	}

	// Question types
	private enum Q { ADD, SUB, MUL, DIV, ALL }

	// Take 10 answers at a time, check for above 75% correct.
	public void start() {
		while (true) {
			setQType();
			setDiff();

			int sol = newQ();
			int correct = 0;

			for (int i = 0; i < 10; i++) {
				int ans = input.nextInt();
				if (ans == sol) {
					commend();
					correct += 1;
					sol = newQ();
				} else
					encourage();
			}

			if (correct < 8)
				System.out.println("Please ask you teacher for extra help.");
			else
				System.out.println("Congratulations,"+
								   "you are ready to go the next level!");
		}
	}

	// Input validation
	private int getPosInt(String msg, int low, int up) {
		System.out.printf(msg);

		int inVal = input.nextInt();
		while (inVal < low || up < inVal) {
			System.out.printf(msg);
			inVal = input.nextInt();
		}
		return inVal;
	}

	private Q intToQ(int n) {
		switch(n) {
			case  1: return Q.ADD;
			case  2: return Q.SUB;
			case  3: return Q.MUL;
			case  4: return Q.DIV;
			default: return Q.ALL;
		}
	}

	private void setQType() {
		String msg = String.format("%s%n\t1\t%s%n\t2\t%s%n\t3\t%s%n"+
								   "\t4\t%s%n\t5\t%s%n%s",
								   "Choose options:",
								   "Addition", "Subtraction",
								   "Multiplication", "Division",
								   "All", "Option: ");
		qtype = intToQ(getPosInt(msg, 1, 5));
	}

	private void setDiff() {
		switch (getPosInt("Choose difficulty level: 1 2 3%nOption: ", 1, 3)) {
			case  1: diff = 1;
			case  2: diff = 10;
			default: diff = 100;
		}
	}

	private int newQ() {
		int a = rand.nextInt(10*diff);
		int b = rand.nextInt(10*diff);
		switch (qtype) {
		case ADD: System.out.println("What is "+a+" plus "+b+"?");
				  return a+b;
		case SUB: System.out.println("What is "+a+" minus "+b+"?");
				  return a-b;
		case MUL: System.out.println("What is "+a+" times "+b+"?");
				  return a*b;
		case DIV: if (b != 0) {
					System.out.println("What is "+a*b+" divided by "+b+"?");
					return a;
				  } else
					return newQ();
		default :  qtype = intToQ(1+rand.nextInt(4));
				  return newQ();
		}
	}

	private void encourage() {
		switch (rand.nextInt(4)) {
			case  0: System.out.println("No. Please Try again.");
			case  1: System.out.println("Wrong. Try once more.");
			case  2: System.out.println("Don't give up!");
			default: System.out.println("No. Keep trying.");
		}
	}

	private void commend() {
		switch (rand.nextInt(4)) {
			case  0: System.out.println("Very good!");
			case  1: System.out.println("Excellent!");
			case  2: System.out.println("Nice work!");
			default: System.out.println("Keep up the good work!");
		}
	}
}
