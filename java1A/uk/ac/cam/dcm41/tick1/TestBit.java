package uk.ac.cam.dcm41.tick1;

public class TestBit {
	public static void main(String[] args) throws Exception {
		long currentValue = Long.decode(args[0]);
		int position = Integer.parseInt(args[1]);
		boolean value = PackedLong.get(currentValue, position);
		System.out.println(value);
	}
}
