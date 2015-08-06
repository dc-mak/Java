// Ex 8.14: Testing Date2.java

public class Date2Test {
	public static void main(String[] args) {
		String[] monthStrings = 
			{"January", "February", "March", "April", "May", "June",
			 "July", "August", "September", "October", "November", "December"};
		for (String m : monthStrings)
			System.out.println(new Date2(m, 14, 2015));
		System.out.println(new Date2(60,2016));
		for (int i = 1; i <= 365; i++)
			System.out.println(new Date2(i,2015));
		// System.out.println(new Date2(366, 2015));
		for (int i = 1; i <= 366; i++)
			System.out.println(new Date2(i, 2016));
	}
}
