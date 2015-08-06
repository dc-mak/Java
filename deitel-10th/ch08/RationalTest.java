// 8.15: Rational.java test

public class RationalTest {
	public static void main(String[] args){
		 Rational x = new Rational(2,4);
		 Rational y = new Rational(9,7);
		 System.out.println(x);
		 System.out.println(y);
		 // System.out.println(x.plus(y));
		 // System.out.println(x.minus(y));
		 System.out.println(y.minus(x).toFloatString());
		 // System.out.println(x.times(x));
		 System.out.println(x.times(y).toFloatString(4));
		 // System.out.println(y.times(y));
		 // System.out.println(y.divide(y));
		 // System.out.println(x.divide(y));
		 // System.out.println(y.divide(x));
		 // System.out.println(x.divide(x));
	}
}
