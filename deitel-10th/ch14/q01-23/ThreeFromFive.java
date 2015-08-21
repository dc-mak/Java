// Ex 14.17: Creating three-letter strings from a five-letter word.
//			 I miss recursion.

public final class ThreeFromFive {
	public static void main(String[] args){
		if (args.length < 1 || args[0].length() != 5)
			return;

		final StringBuilder str = new StringBuilder(args[0]);

		for (int i = 0; i < 5; i++) {
			final char x = str.charAt(i);
			final StringBuilder xs = (new StringBuilder(str)).deleteCharAt(i);
			final StringBuilder[][] xss = new StringBuilder[4][3];

			for (int j = 0; j < 4; j++) {
				final char y = xs.charAt(j);
				final StringBuilder ys = (new StringBuilder(xs)).deleteCharAt(j);

				for (int k = 0; k < 3; k++)
					xss[j][k] = (new StringBuilder())
									.insert(0,y)
									.append(ys.charAt(k));
			}

			for (StringBuilder[] zs : xss)
				for (StringBuilder z : zs)
					System.out.print(z.insert(0,x)+" ");

			System.out.println();
		}
	}
}
