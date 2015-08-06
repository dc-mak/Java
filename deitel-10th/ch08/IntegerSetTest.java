// Ex 8.13: IntegerSet.java testing.

public class IntegerSetTest {
	public static void main(String[] args){
		 IntegerSet s1 = new IntegerSet();
		 IntegerSet s2 = new IntegerSet();
		 IntegerSet s3 = new IntegerSet();
		 IntegerSet s4 = new IntegerSet();
		
		 s1.insert(4);
		 s1.insert(5);
		 s1.insert(33);
		 s1.insert(34);

		 s2.insert(58);
		 s2.insert(30);
		 s2.insert(70);

		 s3.insert(21);
		 s3.insert(5);
		 s3.insert(34);

		 System.out.println("S1 & S2: "+IntegerSet.intersection(s1,s2));
		 s2.insert(4);
		 System.out.println("S1 & S2: "+IntegerSet.intersection(s1,s2));
		 System.out.println("S1 & S3: "+IntegerSet.intersection(s1,s3));
		 System.out.println("S2 & S3: "+IntegerSet.intersection(s2,s3));
	}
}
