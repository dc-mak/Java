// Ex 14.20: Writing a monetary amount as cheque.

import java.util.Scanner;

public final class Cheques {
	public static void main(String[] args){
		final String[] less10 =
			{"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX",
				"SEVEN", "EIGHT", "NINE"};
		final String[] teens =
			{"", "ELEVEN", "TWELVE", "THIR", "", "FIF"};
		final String[] tens = {"", "TEN", "TWEN", "THIR", "", "FIF"};

		final String TEEN = "TEEN";
		final String TY   = "TY";

		Scanner input = new Scanner(System.in);
		System.out.print("Enter amount to cheque: ");
		final double amt = input.nextDouble();
		String[] cheque = String.format("%09.2f", amt).split("\\.");
		StringBuilder chq = new StringBuilder(cheque[0]);
		// After much regexing, this is definitely the simplest solution...
		for (int i = 0; i < chq.length(); i++)
			if (chq.charAt(i) != '0')
				break;
			else
				chq.setCharAt(i, '*');
		System.out.printf("%s.%s%n", chq, cheque[1]);
		
		if (amt > 1000.0)
			return;

		String last2 = "";
		final int amt_trunc = (int) amt;
		final int digit2 = amt_trunc % 100 / 10;
		final int digit3 = amt_trunc % 10;
		final String last = digit3 == 0 ? "" : "-"+less10[digit3];
		switch (digit2) {
			case  0: last2 = less10[digit3]; break;
			case  1: switch (digit3) {
						 case  0: last2 = tens[digit2]; break;
						 case  1:
						 case  2: last2 = teens[digit3]; break;
						 case  3:
						 case  5: last2 = teens[digit3]+TEEN; break;
						 case  8: last2 = less10[digit3]+TEEN.substring(1); break;
						 default: last2 = less10[digit3]+TEEN; break;
					}
					break;
			case  2:
			case  3: 
			case  5: last2 = tens[digit2]+TY+last; break;
			case  8: last2 = less10[digit2]+"Y"+last;
			default: last2 = less10[digit2]+TY+last;
		}
		System.out.printf("%s hundred %s and %s/100%n",
							less10[amt_trunc / 100], last2, cheque[1]);
	}
}
