// Ex 8.4) Create a Rectangle class with defaults as given.

public class Rect {
	private double length = 1;
	private double width  = 1;

	public double getLength() { return length; }
	public double getWidth()  { return width; }

	public void setWidth(double w)  { width  = 20.0 >= w && w > 0.0 ? w : width; }
	public void setLength(double l) { length = 20.0 >= l && l > 0.0 ? l : length; }

}	
