// Ex 20.8: Generic pair class

public final class Pair<F,S> {
	private F first;
	private S secnd;

	public Pair(F first, S secnd) {
		this.first = first;
		this.secnd = secnd;
	}

	public Pair() { /* first = null; secnd = null; */ }

	public F getFirst() { return first; }
	public S getSecnd() { return secnd; }

	public void setFirst(F first) { this.first = first; }
	public void setSecnd(S secnd) { this.secnd = secnd; }
}
