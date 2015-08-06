// Ex 5.23) Drawing patternsa again. There may is definitely a elegant way of
//			doing with only two nested for-loops, but this has been quickly
//			adapted from Ex 5.15.

public class ModPatterns {
	public static void main(String[] args) {
		for (int row = 1; row <= 10; row++) {
			for (int col = 1; col <= 10; col++) {
				if (col <= row)
					System.out.print('*');
				else
					System.out.print(' ');
			}
			System.out.print(' ');
			for (int col = 1; col <= 10; col++) {
				if (col <= 11-row)
					System.out.print('*');
				else
					System.out.print(' ');
			}
			System.out.print(' ');
			for (int col = 1; col <= 10; col++) {
				if (col <= row-1)
					System.out.print(' ');
				else
					System.out.print('*');
			}
			System.out.print(' ');
			for (int col = 1; col <= 10; col++) {
				if (col <= 10-row)
					System.out.print(' ');
				else
					System.out.print('*');
			}
			System.out.println();
		}
	}
}
