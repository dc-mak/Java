// Ex 9.8: Quadrilaterals and inheritance. I use the following.
//		   https://en.wikipedia.org/wiki/Quadrilateral#Taxonomy
//		   I also assume no rotations (which would be simplest
//		   to add in with an extra angle field).

public class Shapes {
	public static void main(String[] args){
		 Trapezium t = new Trapezium(-2, 5, 2, 1, 3, 3);
		 Rectangle r = new Rectangle(8, 9, 3, 4);
		 Square    s = new Square(-4, 3, 5);

		 System.out.printf("Trapezium: (%d, %d), "+
							"(%d, %d), (%d, %d), (%d, %d)%n",
							t.getP1().getX(), t.getP1().getY(),
							t.getP2().getX(), t.getP2().getY(),
							t.getP3().getX(), t.getP3().getY(),
							t.getP4().getX(), t.getP4().getY());

		 System.out.printf("Rectangle: (%d, %d), "+
							"(%d, %d), (%d, %d), (%d, %d)%n",
							r.getP1().getX(), r.getP1().getY(),
							r.getP2().getX(), r.getP2().getY(),
							r.getP3().getX(), r.getP3().getY(),
							r.getP4().getX(), r.getP4().getY());

		 System.out.printf("Square: (%d, %d), "+
							"(%d, %d), (%d, %d), (%d, %d)%n",
							s.getP1().getX(), s.getP1().getY(),
							s.getP2().getX(), s.getP2().getY(),
							s.getP3().getX(), s.getP3().getY(),
							s.getP4().getX(), s.getP4().getY());
	}
}
