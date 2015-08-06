// Ex 9.8: Quadrilaterals and inheritance. I use the following.
//		   https://en.wikipedia.org/wiki/Quadrilateral#Taxonomy
//		   I also assume no rotations (which would be simplest
//		   to add in with an extra angle field).

public class Quadrilateral {
	private final Point p1;
	private final Point p2;
	private final Point p3;
	private final Point p4;

	public Point getP1() { return p1; }
	public Point getP2() { return p2; }
	public Point getP3() { return p3; }
	public Point getP4() { return p4; }

	public Quadrilateral(Point p1, Point p2,
						  Point p3, Point p4) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
	}
}
