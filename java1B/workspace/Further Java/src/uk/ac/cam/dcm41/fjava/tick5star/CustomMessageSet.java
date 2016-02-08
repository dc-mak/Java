package uk.ac.cam.dcm41.fjava.tick5star;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import uk.ac.cam.cl.fjava.messages.Message;

public class CustomMessageSet {
	private final Set<Message> filter;
	private final MultiQueue<Message> mq;
	
	public CustomMessageSet(final MultiQueue<Message> mq) {
		final LinkedHashMap<Message, Boolean> filterMap = new LinkedHashMap<>();
		filter = Collections.synchronizedSet(Collections.newSetFromMap(filterMap));
		this.mq = mq;
	}
	
	public synchronized void register(final MessageQueue<Message> q) {
		mq.register(q);
	}

	public synchronized void deregister(final MessageQueue<Message> q) {
		mq.deregister(q);
	}

	public synchronized void receive(final Message m) {
		if (System.currentTimeMillis() - m.getCreationTime().getTime() < 120_000L && filter.add(m)) {
			mq.put(m);	
		}
	}
	
	public synchronized void mark(final Message m) {
		if (System.currentTimeMillis() - m.getCreationTime().getTime() < 120_000L) {
			filter.add(m);
		}
	}
}