// Ex 4.39: World population for the next 75 years.

public class Population {
	public static void main(String[] args) {
		int limit=75, year=2015;
		double original = 7000000000.0; // 7 bilion
		int year_of_double = 0;

		double rate = 1.1;
		double current  = original;

		System.out.println ("Year\tPopulation\t\tDifference");
		System.out.printf("%d\t%f\t-%n", year, current);
		double prev = current;
		current *= rate;
		int count = 1;
		while (count <= limit) {
			if (current > 2*original)
				if (year_of_double == 0)
					year_of_double = count+year;
			System.out.printf("%d\t%f\t%f%n", count+year, current, current-prev);
			prev = current;
			current *= rate;
			count++;
		}
		System.out.printf("Year of doubling is %d%n.", year_of_double);
	}
}
