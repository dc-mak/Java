package uk.ac.cam.dcm41.tick4;

public class PatternFormatException extends Exception {
	public PatternFormatException(String msg) {
		super("\n"+msg);
	}
}
