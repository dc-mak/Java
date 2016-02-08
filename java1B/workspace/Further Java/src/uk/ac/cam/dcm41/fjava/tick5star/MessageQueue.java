package uk.ac.cam.dcm41.fjava.tick5star;

public interface MessageQueue<T> { // A FIFO queue of items of type T
	void put(T msg); // place msg on back of queue
	T take(); // block until queue length > 0; return head of queue
}