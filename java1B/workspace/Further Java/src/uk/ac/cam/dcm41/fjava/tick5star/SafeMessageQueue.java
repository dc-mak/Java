package uk.ac.cam.dcm41.fjava.tick5star;

public class SafeMessageQueue<T> implements MessageQueue<T> {

	private static class Link<L> {
		L val;
		Link<L> next;

		Link(L val) {
			this.val = val;
			this.next = null;
		}
	}

	private Link<T> first = null;
	private Link<T> last = null;

	@Override
	public synchronized void put(T val) {

		final Link<T> elem = new Link<>(val);
		if (first == null) {
			assert (last == null);
			first = elem;
			last = elem;
		} else {
			assert(last != null);
			last.next = elem;
			last = elem;
		}
		
		this.notify();

	}

	@Override
	public synchronized T take() {

		while (first == null)
			try {
				this.wait();
			} catch (InterruptedException ie) {
			}

		final T result = first == null ? null : first.val;
		if (first != null) first = first.next;

		return result;
	}

}
