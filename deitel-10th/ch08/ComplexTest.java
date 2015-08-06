// Ex 8.11: Very simple complex number class.

public class ComplexTest {
	public static void main(String[] args) {
		Complex c = new Complex();
		Complex d = new Complex(3.0, 4.0);
		c.print();
		c.add(d);
		System.out.printf("Real: %f\tImag: %f%n", c.getReal(), c.getImag());
		System.out.println(c);
		System.out.println(c.subtract(d));
	}
}
