package uk.ac.cam.dcm41.fjava.tick1;

public class HelloWorld {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Hello, world");
		} else {
			System.out.printf("Hello, %s%n", args[0]);
		}
	}
}
