// Ex 13.28: Selecting shapes to be randomly drawn 20 times.

public enum S {
	LINE("Line"), RECT("Rectangle"), OVAL("Oval"); 

	private final String guiName;

	S(String guiName) { this.guiName = guiName; }

	@Override
	public String toString() { return guiName; }
}
