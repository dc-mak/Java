public class Rethrow {
	public static void main(String[] args){
		 try {
			 m1();
		 } catch (Exception e) {
			e.printStackTrace();
		 }
	}
	
	public static void m1() throws Exception {
		try {
			m2();
		} catch (Exception e) {
			throw new Exception("Extra from m1", e);
		}
	}

	public static void m2() throws Exception {
		throw new Exception("From m2");
	}
}
