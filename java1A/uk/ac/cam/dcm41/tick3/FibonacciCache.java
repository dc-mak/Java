package uk.ac.cam.dcm41.tick3;

public class FibonacciCache {
	public static long[] fib = new long [20];

	public static void store() {
		if (fib.length > 0) {
			fib[0] = 1;
			if (fib.length > 1)  {
				fib[1] = 1;		
				for (int index = 2; index < fib.length; index ++) {
					fib[index] = fib[index-1] + fib[index-2];
				}
			}
		}
	}

	public static void reset() {
		for (int index = 0; index < fib.length; index++) {
			fib[index] = 0; 
		}
	}

	public static long get(int i) {
		if ((-1<i)&&(i<fib.length)) {
			return fib[i];
		}
		else return -1L;
	}

	public static void main(String[] args) {
		store();
		for (int index = 0; index < fib.length; index++) {
				System.out.println(get(index));
		}
		reset();
		System.out.println("--");
		for (int index = 0; index < fib.length; index++) {
			System.out.println(get(index));
		}
		System.out.println("-- \n " + get(0));
		System.out.println(get(-1));
	}
}
