// Ex 5.29: Twelve Days of Christmas.

public class Christmas {
	public static void main(String[] args) {
		for (int verse = 1; verse <= 12; verse++) {
			firstLine(verse);
			switch (verse) {
			case 12: System.out.println("Twelve Drummer Drumming");
			case 11: System.out.println("Eleven Pipers Piping");
			case 10: System.out.println("Ten Lords a-Leaping");
			case  9: System.out.println("Nine Ladies Dancing");
			case  8: System.out.println("Eight Maids a-Milking");
			case  7: System.out.println("Seven Swans a-Swimming");
			case  6: System.out.println("Six Geese a-Laying");
			case  5: System.out.println("Five Gold Rings");
			case  4: System.out.println("Four Calling Birds");
			case  3: System.out.println("Three French Hens");
			case  2: System.out.println("Two Turtle Doves");
			case  1: System.out.printf("%sa Partridge in a Pear Tree%n",
							(verse != 1 ? "and " : ""));
			}
			System.out.println();
		}
	}

	private static void firstLine(int day) {
		String nth="";
		switch (day) {
			case  1 : nth = "First";    break;
			case  2 : nth = "Second";   break;
			case  3 : nth = "Third";    break;
			case  4 : nth = "Fourth";   break;
			case  5 : nth = "Fifth";    break;
			case  6 : nth = "Sixth";    break;
			case  7 : nth = "Seventh";  break;
			case  8 : nth = "Eigth";    break;
			case  9 : nth = "Ninth";    break;
			case 10 : nth = "Tenth";    break;
			case 11 : nth = "Eleventh"; break;
			case 12 : nth = "Twelfth";  break;
		}
		System.out.printf("On the %s day of Christmas my true love sent to me%n", nth);
	}
}
