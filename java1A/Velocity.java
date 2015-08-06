public class Velocity implements Cloneable {
	public int size = 8;
	@Override
	public Object clone() throws CloneNotSupportedException {
		Velocity cloned = (Velocity)super.clone();
		cloned.size = 9;
		return cloned;
	}
}
