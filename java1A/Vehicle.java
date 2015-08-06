public class Vehicle implements Cloneable {
	public int age = 10;
	public Velocity v = new Velocity();
	@Override
	public Object clone() throws CloneNotSupportedException {
		Vehicle cloned = (Vehicle)super.clone();
		cloned.v = (Velocity)v.clone();
		cloned.age = 11;
		return (Vehicle)cloned;
	}
}
