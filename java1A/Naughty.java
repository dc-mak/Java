import java.util.LinkedList;
public class Naughty {
	public static void main(String[] args) {
		LinkedList<Double> temp = new LinkedList<Double>();
		temp.add(9.0);
		printAll(temp); // Should fail
	}
/* 
 * Mini-essay. Note that line 4 starts with:
 *		LinkedList				// and not
 *		LinkedList<Double>		// so Java assumes
 *		LinkedList<Object		// which allows line 15
 *	to work in its current form.
 */
	public static void printAll(LinkedList<Object> list) {
		for (Object d : list)
			System.out.println((Double)d);
	}
}
/* http://docs.oracle.com/javase/tutorial/extra/generics/subtype.html */
