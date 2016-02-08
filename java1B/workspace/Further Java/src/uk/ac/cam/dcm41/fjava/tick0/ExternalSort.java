package uk.ac.cam.dcm41.fjava.tick0;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// import java.io.BufferedInputStream;
// import java.io.BufferedOutputStream;
// import java.io.DataInputStream;
// import java.io.DataOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.InputStream;
import java.util.Arrays;
import java.util.PriorityQueue;

import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.Duration;
import java.time.Instant;
import java.util.regex.Matcher;

public class ExternalSort {
								  // = (int) (Runtime.getRuntime().maxMemory() / 12L);
	private static final int INTS = 750_000; // times 4 is ~ 2/3rds of 8MB
	public static final int INT_SIZE = 4;
	private static int CHUNKS = 0; // SHOULD PROBABLY BE LONG
	private static long FILE_SIZE = -1;

	private static void countingSort(String actualFilename, String tmpFilename) 
		throws FileNotFoundException, IOException {

		try(final RandomAccessFile actual = new RandomAccessFile(actualFilename, "r");
			final RandomAccessFile tmp	 = new RandomAccessFile(tmpFilename, "rw")) {

			FILE_SIZE = new File(actualFilename).length();
			/* DEBUG */ System.out.printf("\tSIZE: %d\t", FILE_SIZE);
			// explicit check for later assertion on INTS_READ
			if (FILE_SIZE <= 0L) return; 
			// assert(FILE_SIZE % INT_SIZE == 0L) : "File not composed of integers.";
			// assert(FILE_SIZE < 4L * INT_SIZE * Integer.MAX_VALUE); // I refuse to sort files this big
			CHUNKS = (int) (1L + (FILE_SIZE-1L)/(INTS*INT_SIZE)); // ceiling
			/* DEBUG */ System.out.printf("CHUNKS: %d\t", CHUNKS);

			// custom buffers, adjustable capacity for efficiency
			final byte[] inBuffer = new byte[INTS*INT_SIZE];
			final int MAX_U16  = 65_536; // 2^16
			final int[] count = new int[MAX_U16]; // should this be long?
			final byte[] tmpBuffer = new byte[INTS*INT_SIZE];

			for (int section = 0; section < CHUNKS; section++) {

				// RESET COUNT - DO NOT TOUCH
				Arrays.fill(count, 0);

				// Since actual.read(inBuffer) may not be accurate enough
				int soFar = 0;
				int retVal = 0;
				while (soFar != INTS * INT_SIZE && retVal != -1) {
					retVal = actual.read(inBuffer, soFar, INTS * INT_SIZE - soFar);
					if (retVal != -1) soFar += retVal;
				}
				final int INTS_READ = soFar / INT_SIZE;

				// assertion stuff
				// assert(INTS_READ > 0) : "Empty file";
				// assert(INTS_READ == INTS ^
				//	(section == CHUNKS -1
				//	 && FILE_SIZE % (INTS * INT_SIZE) != 0
				//	 && INTS_READ == (FILE_SIZE % (INTS * INT_SIZE)) / INT_SIZE)) 
				//	: "Incorrect section size";

				// first compare bytes 3&4, without 2's complement conversion
				for (int i = 0; i < INTS_READ; i++) {
					final int indx = ((inBuffer[INT_SIZE*i+2] & 0xff) << 8) | (inBuffer[INT_SIZE*i+3] & 0xff);
					// assert(indx >= 0 && indx < MAX_U16) : "Incorrect 2B conversion";
					count[indx]++;
				}

				for (int i = 1; i < MAX_U16; i++) count[i] += count[i-1];
				// assert(count[MAX_U16-1] == INTS_READ) : "Missing integers";

				for (int i = INTS_READ-1; i >= 0; i--) {

					final int indx = ((inBuffer[INT_SIZE*i+2] & 0xff) << 8) | (inBuffer[INT_SIZE*i+3] & 0xff);
					// // // assert(indx >= 0 && indx < MAX_U16);

					// copy over int
					tmpBuffer[INT_SIZE*(count[indx]-1)] = inBuffer[INT_SIZE*i];
					tmpBuffer[INT_SIZE*(count[indx]-1)+1] = inBuffer[INT_SIZE*i+1];
					tmpBuffer[INT_SIZE*(count[indx]-1)+2] = inBuffer[INT_SIZE*i+2];
					tmpBuffer[INT_SIZE*(count[indx]-1)+3] = inBuffer[INT_SIZE*i+3];

					count[indx]--;
				}

				// tmpBuffer now holds the bytes - DO NOT TOUCH
				Arrays.fill(count, 0);
				Arrays.fill(inBuffer, (byte) 0);

				// now for bytes 1&2, largely the same, but with 2's complement conversion
				final int SGN_I16 = 32_768; // 2^15
				for (int i = 0; i < INTS_READ; i++) {
					final int indx = SGN_I16 + (((tmpBuffer[INT_SIZE*i]) << 8) | (tmpBuffer[INT_SIZE*i+1] & 0xff));
					// assert(indx >= 0 && indx < MAX_U16) : "Incorrect 2B conversion";
					count[indx]++;
				}

				for (int i = 1; i < MAX_U16; i++) count[i] += count[i-1];
				// assert(count[MAX_U16-1] == INTS_READ) : "Missing integers";

				for (int i = INTS_READ-1; i >= 0; i--) {

					final int indx = SGN_I16 + (((tmpBuffer[INT_SIZE*i]) << 8) | (tmpBuffer[INT_SIZE*i+1] & 0xff));
					// // // assert(indx >= 0 && indx < MAX_U16);

					// copy over int
					inBuffer[INT_SIZE*(count[indx]-1)] = tmpBuffer[INT_SIZE*i];
					inBuffer[INT_SIZE*(count[indx]-1)+1] = tmpBuffer[INT_SIZE*i+1];
					inBuffer[INT_SIZE*(count[indx]-1)+2] = tmpBuffer[INT_SIZE*i+2];
					inBuffer[INT_SIZE*(count[indx]-1)+3] = tmpBuffer[INT_SIZE*i+3];

					count[indx]--;

				}

				// Finally, write to the temporary file
				tmp.write(inBuffer, 0, INTS_READ * INT_SIZE);
			}
		}
	}

	private static void transferAndMerge(String actualFilename, String tmpFilename)
		throws FileNotFoundException, IOException {

		final PriorityQueue<CustomInputStream> pq = new PriorityQueue<>(CHUNKS);
		final CustomInputStream[] inputs = new CustomInputStream[CHUNKS];
		final int BUFFER_SIZE = (INTS / CHUNKS) * INT_SIZE;
		/* DEBUG */	System.out.printf("INTS: %d\tBUFFER_SIZE: %d%n", INTS, BUFFER_SIZE);

		// one output for original file
		try (final RandomAccessFile output = new RandomAccessFile(actualFilename, "rw")) {

			// create multiple pointers to the correct point and add them to the queue
			for (int section = 0; section < CHUNKS; section++) {

				final long count =
					section == CHUNKS-1 && FILE_SIZE % (INTS * INT_SIZE) != 0 ?
						(FILE_SIZE % (INTS*INT_SIZE)) / INT_SIZE : INTS;

				inputs[section] = new CustomInputStream(tmpFilename, BUFFER_SIZE, count, 
				// ASSUMPTION: NO RISK OF OVERFLOW DUE TO CAST
						(long) section*INTS*INT_SIZE);

				pq.offer(inputs[section]);
			}


			final byte[] outBytes = new byte[INTS * 9 / 5];
			int top = 0;

			while (!pq.isEmpty()) {

				final CustomInputStream input = pq.poll();	
				final int outVal = input.readInt();
				if (!input.isDone()) {
					pq.offer(input);
				} else {
					input.close();
				}

				if (top == outBytes.length) {
					output.write(outBytes);
					top = 0;
				}
				outBytes[top] = (byte) ((outVal >> 24) & 0xff);
				outBytes[top + 1] = (byte) ((outVal >> 16) & 0xff);
				outBytes[top + 2] = (byte) ((outVal >> 8) & 0xff);
				outBytes[top + 3] = (byte) (outVal & 0xff);
				top += 4;
				
			}

			output.write(outBytes, 0, top);
			// assert(pq.size() == 0) : "P Queue is non-empty.";
		}
	}

	public static void sort(String actualFilename, String tmpFilename)
		throws FileNotFoundException, IOException {

		countingSort(actualFilename, tmpFilename);
		if (CHUNKS == 0) return;
		// tmpFilename now contains CHUNKS of maximum INTS sized blocks of sorted integers
		transferAndMerge(actualFilename, tmpFilename);
	}

	private static String byteToHex(byte b) {
		String r = Integer.toHexString(b);
		if (r.length() == 8) {
			return r.substring(6);
		}
		return r;
	}

	public static String checkSum(String f) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			DigestInputStream ds = new DigestInputStream(
					new FileInputStream(f), md);
			byte[] b = new byte[512];
			while (ds.read(b) != -1)
				;

			String computed = "";
			for(byte v : md.digest()) 
				computed += byteToHex(v);

			return computed;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "<error computing checksum>";
	}

	public static void main(String[] args) throws Exception {
		if (args.length == 2) {

		 final String f1 = args[0];
		 final String f2 = args[1];
		 sort(f1, f2);
		 System.out.println("The checksum is: "+checkSum(f1));

		} else if (args.length == 3 &&
					(args[0].equals("--file") || args[0].equals("--batch"))) {

			try {

				final boolean BATCH = args[0].equals("--batch");
				final int START;
				final int END;

				if (BATCH) {
					START = Integer.parseInt(args[1]);
					END = Integer.parseInt(args[2]);

					if (START < 1 || END < START) {
						System.out.println("Invalid range: enter 0 < start <= end");
						return;
					}
				} else {
					START = 0;
					END = 0;
				}

				final Scanner checksum_test = new Scanner(
						Paths.get("./uk/ac/cam/dcm41/fjava/tick0/checksum.txt"));

				final Pattern p = Pattern.compile("(?:\\d++\\s*:\\s*)(?<sum>\\p{XDigit}+)");
				
				for (int i = START; i <= END; i++) {

					final String f1;
					final String f2;
					if (BATCH) {
						f1 = "uk/ac/cam/dcm41/fjava/tick0/test"+i+"a.dat";
						f2 = "uk/ac/cam/dcm41/fjava/tick0/test"+i+"b.dat";
					} else {
						f1 = "uk/ac/cam/dcm41/fjava/tick0/"+args[1];
						f2 = "uk/ac/cam/dcm41/fjava/tick0/"+args[2];
					}

					final Instant start = Instant.now();
					sort(f1, f2);
					final Instant end = Instant.now();
					final long time = Duration.between(start, end).toMillis();


					if (i >= 1 && i <= 18) {
						final String chck = checkSum(f1);
						final Matcher m   = p.matcher(checksum_test.nextLine());
						final String cmp  = m.find() ? m.group("sum") : "[NOT FOUND]";
						
						System.out.printf("[SORTED] %d %s %s%n", i, chck,
								(chck.equals(cmp) ? "\t"+Long.toString(time)+" (ms)"
													: " FAIL: "+cmp));
					} else {
						System.out.printf("[SORTED] %d (%d ms) %s%n", i, time, checkSum(f1));
					}

					// RESET VALS
					CHUNKS = 0;
					FILE_SIZE = -1;
				}

			} catch (NumberFormatException nfe) {
				System.out.println("Numbers only: enter 0 < start <= end");
				return;
			}

		} else {

			System.out.printf("Either: %n\t%s%n\t%s%n\t%s%n",
					"<fullPathA> <fullPathB>",
					"--file <shortPathA> <shortPathB>",
					"--batch <start_index> <end_index>");

		}
	}
}
