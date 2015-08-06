package uk.ac.cam.acr31.sound;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;

/**
 * Represents a sequence of sounds to be written to a WAV file. The sound file
 * generated consists of timeslots to which you some number of Sounds. An
 * example usage is as follows: AudioSequence as = new AudioSequence(0.1);
 * as.addSound(new SineWaveSound(0.2)); as.addSound(new SineWaveSound(0.5));
 * as.advance(); as.addSound(new SineWaveSound(0.5)); as.write(new
 * FileOutputStream("/tmp/file.wav"));
 * 
 * @author acr31
 */
public class AudioSequence {

	/**
	 * Sample rate of the output WAV file
	 */
	public static final int SAMPLES_PER_SECOND = 8000;

	/**
	 * The number of samples in the WAV file for each timeslot
	 */
	private int samplesPerTimeslot;

	/**
	 * A list of timeslots. Each timeslot is a further list of Sound objects
	 * indicating what to play (concurrently) in that slot
	 */
	private LinkedList<LinkedList<Sound>> sequence = new LinkedList<LinkedList<Sound>>();

	/**
	 * The sounds in the current timeslot
	 */
	private LinkedList<Sound> current;

	/**
	 * Create an AudioSequence object where each timeslot with have the duration
	 * given.
	 * 
	 * @param timeslotDurationSeconds
	 */
	public AudioSequence(double timeslotDurationSeconds) {
		this.samplesPerTimeslot = (int) (timeslotDurationSeconds * SAMPLES_PER_SECOND);
		current = new LinkedList<Sound>();
		sequence.add(current);
	}

	/**
	 * Add this sound to the currently playing timeslot
	 * 
	 * @param s
	 */
	public void addSound(Sound s) {
		current.add(s);
	}

	/**
	 * Append a new empty timeslot on the end of the file and get ready to fill
	 * it
	 */
	public void advance() {
		current = new LinkedList<Sound>();
		sequence.add(current);
	}

	/**
	 * Convert an integer in to 4 bytes in little endian order
	 * 
	 * @param v
	 * @return
	 */
	private byte[] integerLE(int v) {
		return new byte[] {(byte) (v & 0xff), (byte) ((v >>> 8) & 0xff), (byte) ((v >>> 16) & 0xff),
							(byte) ((v >>> 24) & 0xff) };
	}

	private byte[] shortLE(int s) {
		return new byte[] {(byte) (s & 0xff), (byte) ((s >>> 8) & 0xff) };
	}

	/**
	 * Write the WAV file to the output stream given
	 * 
	 * @param os
	 * @throws IOException
	 */
	public void write(OutputStream os) throws IOException {
		int dataLength = sequence.size() * samplesPerTimeslot * 2; // 2 bytes
		// per
		// sample
		writeHeader(os, dataLength);

		for (LinkedList<Sound> sounds : sequence) {
			short[] sampleBlock = new short[samplesPerTimeslot];
			for (Sound s : sounds) {
				s.addToSamples(sampleBlock);
			}
			for (int i = 0; i < sampleBlock.length; ++i) {
				os.write(sampleBlock[i] & 0xff);
				os.write((sampleBlock[i] >>> 8) & 0xff);
			}
		}
		os.flush();
	}

	/**
	 * Write a WAV file header to the stream given
	 * 
	 * @param os
	 * @param dataLength
	 * @throws IOException
	 */
	private void writeHeader(OutputStream os, int dataLength) throws IOException {
		int fileLength = 12 + // RIFF CHUNK
		24 + // FORMAT CHUNK
		dataLength + 8; // DATA CHUNK

		int packageLength = fileLength - 8;

		os.write("RIFF".getBytes());
		os.write(integerLE(packageLength));
		os.write("WAVE".getBytes());

		// FORMAT CHUNK
		os.write("fmt ".getBytes());
		os.write(integerLE(16));
		os.write(shortLE(1));
		os.write(shortLE(1)); // mono
		os.write(integerLE(SAMPLES_PER_SECOND)); // sample rate
		os.write(integerLE(SAMPLES_PER_SECOND * 2)); // bytes per
		// second
		os.write(shortLE(2)); // bytes per sample
		os.write(shortLE(16)); // bits per sample

		// DATA CHUNK
		os.write("data".getBytes());
		os.write(integerLE(dataLength));
	}
}
