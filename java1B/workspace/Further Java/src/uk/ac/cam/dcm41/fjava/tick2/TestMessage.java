package uk.ac.cam.dcm41.fjava.tick2;

import java.io.Serializable;

public class TestMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String text;
	public String getMessage() {return text;}
	public void setMessage(String msg) {text = msg;}
}