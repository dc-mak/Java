package uk.ac.cam.dcm41.tick6star;

public class PatternFormatException extends Exception {
	public PatternFormatException(String msg) {
		super("\n"+msg);
	}
}
