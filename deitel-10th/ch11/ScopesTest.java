public class ScopesTest {
	public static void main(String[] args){
		try {
			 Scopes s = new Scopes("0"); // "hello", or "2"
			 System.out.println(s.a);
		} catch (NumberFormatException nfe) {
			System.out.println("Oops");
		}
	}
}
