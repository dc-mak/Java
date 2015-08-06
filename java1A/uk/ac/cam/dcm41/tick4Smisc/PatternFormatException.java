package uk.ac.cam.dcm41.tick4Smisc;

public class PatternFormatException extends Exception {
	public PatternFormatException(String msg) {
		super("\n"+msg);
	}
}
