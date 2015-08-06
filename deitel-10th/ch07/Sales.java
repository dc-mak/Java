// Ex 7.10: Frequency of sales:

import java.util.Arrays;

public class Sales {
	public static void main(String[] args) {
		int[] sales = {234, 938, 543, 352, 208, 745, 670, 453, 749, 1000};
		 
		final int SIZE = 11;
		int[] freq = new int[SIZE];

		Arrays.fill(freq, 0);
		for (int salary : sales)
			if (salary >= 200)
				if (salary < 1000)
					freq[salary/100] += 1;
				else
					freq[freq.length-1] += 1;

		System.out.printf("%-8s\t%s%n", "Range", "Frequency");

		for (int i = 2; i < freq.length; i++)
			if (i != freq.length-1)
				System.out.printf("$%3d-%3d\t%d%n", i*100, (i+1)*100-1, freq[i]);
			else
				System.out.printf("$%,6d+\t%d%n", i*100, freq[i]);
	}
}
