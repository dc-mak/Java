package uk.ac.cam.dcm41.fjava.tick3star;

public interface ConcurrentQueue<T> {
	public void offer(T message); // Add "message" to queue
	public T poll();			  // Return first item from queue or null if empty
}
