public class Order {
	public static void main(String[] args){
		 try {
			 m1();
		 } catch (ExceptionA a) {
			 System.out.print("All go here.");
		 } catch (ExceptionB b) {
			System.out.println("Not reaced.");
		 } catch (ExceptionC c) {
			 System.out.println("No chance of reached.");
		 }
	}

	public static void m1() throws ExceptionC {
		throw new ExceptionC();
	}
}
