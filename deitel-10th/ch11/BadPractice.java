import java.io.IOException;

public class BadPractice {
	public static void main(String[] args){
		 try { m1(); } catch (Exception e) { System.out.println("A caught"); }
		 try { m2(); } catch (Exception e) { System.out.println("B caught"); }
		 try { m3(); } catch (Exception e) { System.out.println("NullPointerException caught"); }
		 try { m4(); } catch (Exception e) { System.out.println("IOException caught"); }
	}

	public static void m1() throws ExceptionA {
		System.out.println("Throwing A...");
		throw new ExceptionA();
	}

	public static void m2() throws ExceptionB {
		System.out.println("Throwing B...");
		throw new ExceptionB();
	}

	public static void m3() {
		System.out.println("Throwing NullPointerException...");
		throw new NullPointerException();
	}

	public static void m4() throws IOException {
		System.out.println("Throwing IOException...");
		throw new IOException();
	}
}
