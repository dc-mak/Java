package uk.ac.cam.dcm41.fjava.tick0;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.Closeable;
import java.io.FileNotFoundException;

public final class CustomInputStream implements Comparable<CustomInputStream>, Closeable {

	private static final int INT_SIZE = ExternalSort.INT_SIZE;
	private final byte[] BUFFER;
	private long elemCount;
	private int top = 0;
	private final RandomAccessFile RA_File;

	public boolean isDone() { return elemCount == 0L; }

	public CustomInputStream(String file, int BUFFER_SIZE, long elemCount, long seekAmt)
			throws FileNotFoundException, IOException {

		if (elemCount <= 0)
			throw new IllegalArgumentException("Capacity must be greater than 0.");
		this.elemCount = elemCount;

		RA_File = new RandomAccessFile(file, "r");
		// assert(BUFFER_SIZE > 0 && BUFFER_SIZE % INT_SIZE == 0)
		// 	: "[CUSTOM] Incorrect BUFFER_SIZE ("+BUFFER_SIZE+")";
		BUFFER = new byte[BUFFER_SIZE];

		RA_File.seek(seekAmt);
		// more robust read into byte array
		int soFar = 0;
		int retVal = 0;
		while(soFar < BUFFER.length && retVal != -1) {
			retVal = RA_File.read(BUFFER, soFar, BUFFER.length - soFar);
			if (retVal != -1) soFar += retVal;
		}
	}

	// assertions in topInt():
	//	   elemCount > 0L
	//	   0 <= top && top + INT_SIZE <= BUFFER.length.
	@Override
	public int compareTo(CustomInputStream b) {
		return Integer.compare(topInt(), b.topInt());
	}

	// Inline in necessary contexts? MEASURE!
	private int topInt() {
		// assert(elemCount > 0L) : "[CUSTOM] NO ELEMENTS";
		// assert(0 <= top && top + INT_SIZE <= BUFFER.length) : "[CUSTOM] END OF BUFFER (PRE)";

		return ((BUFFER[top] << 24)
				| ((BUFFER[top+1] & 0xff) << 16)
				| ((BUFFER[top+2] & 0xff) << 8)
				| ((BUFFER[top+3] & 0xff)));
	}

	// INVARIANT: 0 <= top && top + INT_SIZE <= BUFFER.length.
	//	  This guarantees that every call of topInt() is successful.
	// Because constructor ensure BUFFER.length % 4 == 0, we can not get a
	// EOFException equivalent situation because file is buffer size is guaranteed 
	// to always have complete, 4B integers.
	public int readInt() throws IOException {

		// assertions in topInt():
		//	   elemCount > 0L
		//	   0 <= top && top + INT_SIZE <= BUFFER.length.
		final int result = topInt();

		top += INT_SIZE;
		if (top == BUFFER.length) {

			// more robust read into byte array
			top = 0;
			int soFar = 0;
			int retVal = 0;
			while (soFar < BUFFER.length && retVal != -1) {
				retVal = RA_File.read(BUFFER, soFar, BUFFER.length - soFar);
				if (retVal != -1) soFar += retVal;
			}

		}

		elemCount--;
		// assert(0 <= top && top + INT_SIZE <= BUFFER.length)
		// 	: String.format("[CUSTOM] END OF BUFFER (POST) -- top = %d," +
		// 					"BUFFER.length = %d", top, BUFFER.length);
		return result;
	}

	@Override
	public void close() throws IOException { RA_File.close(); }
}
