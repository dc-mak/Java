// 5.21: Pythagorean triples.

public class PythagTriples {
	public static void main (String[] args) {
		for (int a = 1; a <= 500; a++) {
			for (int b = 1; b <= 500; b++) {
				for (int c = 1; c <= 500; c++) {
					if (a*a + b*b == c*c) {
						System.out.printf ("%,7d\t%,7d\t%,7d%n", a, b, c); }}}}}}
