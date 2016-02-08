package uk.ac.cam.dcm41.fjava.tick3star;

public class ConcurrentQueueTest {

	private ConcurrentQueue<String> q;

	private class Producer extends Thread {
		private int sent = 0;

		public void run() {
			for (int i = 0; i < 50000; ++i) {
				q.offer("" + i);
				sent++;
			}
		}

		public int numberProduced() {
			return sent;
		}
	}

	private class Consumer extends Thread {
		private int recv = 0;

		public void run() {
			String r;
			while ((r = q.poll()) == null || !r.equals("EOF")) {
				if (r != null) {
					recv++;
				} else {
					Thread.yield();
				}
			}
			q.offer("EOF");
		}

		public int numberConsumed() {
			return recv;
		}
	}

	private Consumer[] consumers;
	private Producer[] producers;

	ConcurrentQueueTest(ConcurrentQueue<String> q, int c, int p) {
		this.q = q;
		consumers = new Consumer[c];
		for (int i = 0; i < c; ++i)
			consumers[i] = new Consumer();
		producers = new Producer[p];
		for (int i = 0; i < p; ++i)
			producers[i] = new Producer();
	}

	public boolean run() {

		for (Consumer c : consumers)
			c.start();
		for (Producer p : producers)
			p.start();
		for (Producer p : producers)
			try {
				p.join();
			} catch (InterruptedException e) {
			}
		q.offer("EOF");
		for (Consumer c : consumers)
			try {
				c.join(10000);
			} catch (InterruptedException e) {
			}

		int recv = 0;
		for (Consumer consumer : consumers)
			recv += consumer.numberConsumed();
		int sent = 0;
		for (Producer p : producers)
			sent += p.numberProduced();
		System.out.println(q.getClass().getSimpleName() + "(" + consumers.length + "," + producers.length + "):\t"
				+ (recv == sent ? "PASS" : "FAIL"));
		return recv == sent;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; ++i) {
			if (!new ConcurrentQueueTest(new OneLockConcurrentQueue<String>(), 5, 5).run())
				return;
			if (!new ConcurrentQueueTest(new TwoLockConcurrentQueue<String>(), 5, 5).run())
				return;
			if (!new ConcurrentQueueTest(new NoLockConcurrentQueue<String>(), 5, 5).run())
				return;
		}
	}

}
