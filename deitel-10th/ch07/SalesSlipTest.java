// Ex 7.20: Total sales per product and per 

import java.util.Arrays;
import java.security.SecureRandom;
import java.util.Scanner;

public class SalesSlipTest {
	public static void main(String[] args) {
		SecureRandom rand = new SecureRandom();

		final int PEOPLE = SalesSlip.PEOPLE;
		final int PRODS  = SalesSlip.PRODS;

		// Sales by product and person
		double[][] sales = new double[PRODS][PEOPLE];
		for (double[] arr : sales)
			Arrays.fill(arr, 1.0);

		// Last month's record
		SalesSlip[] record = new SalesSlip[100];
		for (int i = 0; i < record.length; i++)
			record[i] = new SalesSlip(1+rand.nextInt(PEOPLE),
									  1+rand.nextInt(PRODS),
									  200*rand.nextDouble()); 

		// Input into table
		for (SalesSlip s : record)
			sales[s.getProduct()-1][s.getPerson()-1] += s.getValue();

		// Table header
		System.out.printf("Prod/Person");
		for (int person = 0; person < PEOPLE; person++)
			System.out.print("\t"+(person+1));
		System.out.printf("\tTotal%n");

		// Table data
		double[] person_total = new double[PEOPLE];
		Arrays.fill(person_total, 0.0);
		for (int prod = 0; prod < PRODS; prod++) {
			System.out.print("\t"+(prod+1));
			double prod_total = 0.0;
			for (int person = 0; person < PEOPLE; person++) {
				double sale = sales[prod][person];
				person_total[person] += sale;
				prod_total += sale;
				System.out.printf("\t%.2f", sale);
			}
			System.out.printf("\t%.2f%n", prod_total);
		}

		// Table totals
		System.out.print("\tTotal");
		for (int person = 0; person < PEOPLE; person++)
			System.out.printf("\t%.2f", person_total[person]);
		System.out.println();
	}
}
