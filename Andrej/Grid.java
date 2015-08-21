import java.lang.Math;

public class Grid {
	public static void main(String[] args){
		final int m = Integer.parseInt(args[0])/2;
		for (int i = -m; i <= m; i++) {
			for (int j = -m; j <= m; j++)
				System.out.print((m+1-Math.max(Math.abs(i), Math.abs(j))));
			System.out.println();
		}
	}
}
