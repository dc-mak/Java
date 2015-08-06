// Ex 9.8: Quadrilaterals and inheritance. I use the following.
//		   https://en.wikipedia.org/wiki/Quadrilateral#Taxonomy
//		   I also assume no rotations (which would be simplest
//		   to add in with an extra angle field).

public class Rectangle extends Trapezium {
	public Rectangle(int x, int y, int w, int h) {
		super (x, x+w, y, x, x+w, y+h);
	}
}
