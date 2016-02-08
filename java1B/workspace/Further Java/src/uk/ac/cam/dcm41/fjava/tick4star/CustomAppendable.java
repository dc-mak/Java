package uk.ac.cam.dcm41.fjava.tick4star;

import javax.swing.JTextArea;

public class CustomAppendable implements Appendable {

	private final JTextArea textArea;
	
	public CustomAppendable (final JTextArea textArea) {
		this.textArea = textArea;
	}
	
	@Override
	public Appendable append(char c) {
		textArea.append(String.valueOf(c));
		return this;
	}
	
	@Override
	public Appendable append(CharSequence csq) {
		textArea.append(String.valueOf(csq));
		return this;
	}
	
	@Override
	public Appendable append(CharSequence csq, int start, int end) {
		textArea.append(csq.subSequence(start, end).toString());
		return this;
	}
	
}
