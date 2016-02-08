package uk.ac.cam.dcm41.fjava.tick3;

public class UnsafeMessageQueue<T> implements MessageQueue<T> {

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
	public void put(T val) {

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

	}

	@Override
	public T take() {

		while (first == null)
			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {
			}

		final T result = first == null ? null : first.val;
		if (first != null) first = first.next;

		return result;
	}

}
