public class ShapeTest {
	public static void main(String[] args){
		 final Shape[] shapes = {new Circle(4.0),
									new Square(3.0),
									new Triangle(2.0, 6.0),
									new Sphere(5.0),
									new Cube(9.0),
									new Tetrahedron(7.0)};

		 for (Shape s : shapes)
			 System.out.printf("%s, Area: %f %s%n",
					 s.getClass().getName(),
					 s.getArea(),
					 s instanceof ThreeDShape ?
						 "Volume: "+((ThreeDShape)s).getVolume() : "");
	}
}
