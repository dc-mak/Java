// Ex 8.11: Very simple complex number class.

public class Complex {
	private double real;
	private double imag;

	public double getReal() { return real; }
	public double getImag() { return imag; }

	public void setReal(double r) { real = r; }
	public void setImag(double i) { imag = i; }

	public Complex() { this(0.0, 0.0); }
	public Complex(double r, double i) { real = r; imag = i; }

	public Complex add(Complex c) {
		real = getReal() + c.getReal();
		imag = getImag() + c.getImag();
		return this;
	}

	public Complex subtract(Complex c) {
		real = getReal() - c.getReal();
		imag = getImag() - c.getImag();
		return this;
	}

	public String toString() { return "("+getReal()+", "+getImag()+")"; }

	public void print() { System.out.println(this); }
}
