// Ex 9.8: Quadrilaterals and inheritance. I use the following.
//		   https://en.wikipedia.org/wiki/Quadrilateral#Taxonomy

public class Trapezium extends Quadrilateral {
	public Trapezium(int x1, int x2, int y12, int x3, int x4, int y34) {
		super(new Point(x1, y12), new Point(x2, y12),
				new Point(x3, y34), new Point(x4, y34));
	}
}
