package uk.ac.cam.dcm41.fjava.tick0;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.IOException;

// Losing a bit of cohesion and increasing coupling with this but it's
// arguably simpler. However, overhead of calls stack for compareTo???
class CountedInputStream extends DataInputStream implements Comparable<CountedInputStream> {

	private long elem_count;

	public CountedInputStream(InputStream in, long elem_count) {
		super(in);
		if (elem_count <= 0) 
			throw new IllegalArgumentException("Capacity must be greater than 0.");
		this.elem_count = elem_count;
	}

	public void decrement() { elem_count--; }

	public boolean isDone() { return elem_count == 0L; }

	@Override
	public int compareTo(CountedInputStream b) {

		mark(ExternalSort.INT_SIZE);
		b.mark(ExternalSort.INT_SIZE);

		int result = 0;
		try {

			final int aVal = readInt();
			final int bVal = b.readInt();
			if (aVal < bVal) {
				result = -1;
			} else if (aVal > bVal) {
				result = 1;
			}
			reset();
			b.reset();

		} catch (IOException e) {

			System.out.println("[ERROR]: During comparison.");
			e.printStackTrace();
			reset();
			b.reset();

		} finally { return result; }
	}
}
