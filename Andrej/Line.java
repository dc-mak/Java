import java.lang.Math.*;

public class Line {
	public static void main(String[] args){
		// java Line x1 y1 x2 y2
		final int x = Math.abs(Integer.parseInt(args[0])-Integer.parseInt(args[2]));
		final int y = Math.abs(Integer.parseInt(args[1])-Integer.parseInt(args[3]));

		int total = 0;
		for (int m = 1; m < (int) Math.sqrt(x); m++)
			for (int n = 1; n < m; n++)
				if (2*m*n*x == y*(m*m - n*n)) {
					total++;
					break;
				}
		System.out.println(total);
	}
}

/*
 * Write a program that takes as its input four integers, representing two
 * points A and B in a plane with integer coordinates, and outputs the number
 * of points with integer coordinates that are on the line segment AB.
 *
 * Find how many sets of integers (a,b,c) satisfy,
 *
 *					a <= x = |x2-x1| and b <= y = |y2-y1| where
 *								k(a^2 + b^2) = k*c^2
 * 
 * Use Pythogorean triples: a = m^2 - n^2, b = 2mn, (c = m^2 + n^2) where m > n.
 *
 *			b/a = y/x => 2mn /(m^2 - n^2) = y/x => 2mx = y(m^2 - n^2).
 *
 * Since m^2 - n^2 <= x, m < floor (Math.sqrt (x)).
 *                                                                           */
