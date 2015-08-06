public class test {
	public static void main (String args[]) {
		try {
			Vehicle vA = new Vehicle();
			Vehicle vB = (Vehicle)vA.clone();
			System.out.println(vA.age+" "+vA.v.size);
			System.out.println(vB.age+" "+vB.v.size);
		} catch (CloneNotSupportedException cnse) {
			System.out.println("WTF");
		} finally {
			return; } } }
