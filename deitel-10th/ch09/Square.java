// Ex 9.8: Quadrilaterals and inheritance. I use the following.
//		   https://en.wikipedia.org/wiki/Quadrilateral#Taxonomy
//		   I also assume no rotations (which would be simplest
//		   to add in with an extra angle field).

public class Square extends Rectangle {
	public Square(int x, int y, int s) {
		super(x, y, s, s);
	}
}
