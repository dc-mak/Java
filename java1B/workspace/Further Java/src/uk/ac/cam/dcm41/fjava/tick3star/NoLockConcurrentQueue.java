package uk.ac.cam.dcm41.fjava.tick3star;

import java.util.concurrent.atomic.AtomicReference;

public class NoLockConcurrentQueue<T> implements ConcurrentQueue<T> {

	private static class Link<L> {
		final L val;
		final AtomicReference<Link<L>> nextRef = new AtomicReference<>();

		Link(final L val) {
			this.val = val;
		}
	}

	private AtomicReference<Link<T>> qHead = new AtomicReference<>(new Link<>(null));
	private AtomicReference<Link<T>> qTail = new AtomicReference<>(qHead.get());

	@Override
	public synchronized void offer(T val) {

		final Link<T> node = new Link<>(val);
		while (true) {
			final Link<T> tail = qTail.get();
			final Link<T> next = tail.nextRef.get();
			if (tail == qTail.get()) {
				if (next == null) {
					if (tail.nextRef.compareAndSet(next, node)) {
						qTail.compareAndSet(tail, node);
						return;
					}
				} else {
					qTail.compareAndSet(tail, next);
				}
			}
		}

	}

	@Override
	public synchronized T poll() {

		while (true) {
			final Link<T> head = qHead.get();
			final Link<T> tail = qTail.get();
			final Link<T> next = head.nextRef.get();
			if (head == qHead.get()) {
				if (head == tail) {
					if (next == null) {
						return null;
					}
					qTail.compareAndSet(tail, next);
				} else if (qHead.compareAndSet(head, next)) {
					return next.val;
				}
			}
		}
	}

}
