public class ABCTest {
	public static void main(String[] args){
		try {
			method1();
		} catch (ExceptionA b) {
			System.out.println("B caught");
			try {
				method2();
			} catch (ExceptionA c) {
				System.out.println("C caught");
			} finally {
				System.out.println("Finally of C");
			}
		} finally {
			System.out.println("Finally of B");
		}
	}

	private static void method1() throws ExceptionB {
		System.out.println("Throwing B...");
		throw new ExceptionB();
	}

	private static void method2() throws ExceptionC {
		System.out.println("Throwing C...");
		throw new ExceptionC();
	}
}
