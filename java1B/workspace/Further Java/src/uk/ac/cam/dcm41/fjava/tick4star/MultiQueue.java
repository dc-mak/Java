package uk.ac.cam.dcm41.fjava.tick4star;

import java.util.HashSet;
import java.util.Set;

public class MultiQueue<T> {
	final private Set<MessageQueue<T>> outputs = new HashSet<>();
	
	public synchronized void register(MessageQueue<T> q) {
		outputs.add(q);
	}
	
	public synchronized void deregister(MessageQueue<T> q) {
		outputs.remove(q);
	}
	
	public synchronized void put(T message) {
		// Could be parallel for each for pretentiousness
		outputs.forEach(mq -> mq.put(message));
	}
	
}
