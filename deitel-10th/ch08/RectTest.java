// Ex 8.4: Test Rect class.

public class RectTest {
	public static void main(String[] args) {
		Rect r = new Rect();

		System.out.printf("Inital:  %f x %f%n", r.getLength(), r.getWidth());
		r.setLength(21.0);
		r.setWidth(4.0);
		System.out.printf("Inital:  %f x %f%n", r.getLength(), r.getWidth());
	}
}
