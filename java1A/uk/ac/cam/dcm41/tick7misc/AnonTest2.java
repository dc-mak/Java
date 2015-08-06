package uk.ac.cam.dcm41.tick7misc;

public class AnonTest2 { private int counter;

	public class A { // A is visible outside of AnonTest2
		private A() {} // A can only be constructed inside AnonTest2
		public void print() { System.out.println("A: "+counter); }
	}

	public AnonTest2() {
		counter = 0;
	}

	public void incrementCounter() {
		counter++;
	}

	public A getA() {
		A instance1 = new A();
		return instance1;
	}

	public A getSpecialA() {
		A instance2 = new A() {
			public void print() { System.out.println("Special: "+counter); }
		};
		return instance2;
	}
}
