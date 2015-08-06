package uk.ac.cam.dcm41.tick7misc;

public class AnonTest1 {

	private static class A {
		public void print() { System.out.println("I am A!"); }
	}

	public static void main(String[] args) {
		A instance1 = new A();
		instance1.print();

		A instance2 = new A() {				//new instance of A
			public void print() {			//overriding the print method.
			   	System.out.println("I am more than A!"); 
			}
		};

		instance2.print();
	}
}
