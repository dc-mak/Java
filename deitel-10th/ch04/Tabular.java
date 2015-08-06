// Ex 4.22: Look at it.

public class Tabular {
	public static void main(String[] args) {
		int count = 1;
		System.out.println("N\t10*N\t100*N\t1000*N");
		while(count <= 10) {
			// Could do it with two loops but c'mon
			System.out.printf("%d\t%d\t%d\t%d%n",
					count, 10*count, 100*count, 1000*count);
			count++;
		}
	}
}
