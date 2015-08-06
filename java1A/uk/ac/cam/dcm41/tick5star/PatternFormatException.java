package uk.ac.cam.dcm41.tick5star;

public class PatternFormatException extends Exception {
	public PatternFormatException(String msg) {
		super("\n"+msg);
	}
}
