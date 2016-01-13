// Ex 21.9,20,21,26-27: List manipulations.

public class ListTest {
	public static void main(String[] args) {
		final int[] test = {4, 3, 5, 6, 8, 43, 26, 24};

		final List<Integer> list = new List<>();
		
		for (int i : test)
			list.insertAtBack(i);

		list.print();
		list.insertAt(0, -1);
		list.print();
		list.insertAt(3, -1);
		list.print();
		try { list.insertAt(15, -1); }
		catch (IndexOutOfBoundsException e) { System.out.println(e); }
		list.insertAt(9, -1);
		list.print();
		list.insertAt(10, -1);
		list.print();
		list.deleteAt(11);
		list.print();
		try { list.deleteAt(11); }
		catch (IndexOutOfBoundsException e) { System.out.println(e); }
		list.print();
		list.removeFromBack();
		list.removeFromBack();
		list.removeFromBack();
		list.print();
	} 
} 
