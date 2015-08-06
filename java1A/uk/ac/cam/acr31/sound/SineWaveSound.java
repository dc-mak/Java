package uk.ac.cam.acr31.sound;

/**
 * Generates a sine wave with the chosen frequency and amplitude scale. No
 * end-of-sample normalization is done which means that the signal might end
 * somewhere other than a zero crossing. This will cause pops in the sound. Use
 * a filter to fix this.
 * 
 * @see ADSREnvelopeFilter
 * @see RectangularLowPassFilter
 */
public class SineWaveSound extends PeriodicSound {

	/**
	 * Create a new sine wave
	 * 
	 * @param frequency
	 *            of the signal (@see Frequency}
	 * @param amplitudeScale
	 *            should be between 0 and 1 to indicate the amplitude of the
	 *            signal. Warning: if you use 1 here then you'll be using the
	 *            full amplitude range which means that you'll get distortion
	 *            when you combine two sounds together.
	 */
	public SineWaveSound(double frequency, double amplitudeScale) {
		super(frequency, amplitudeScale);
	}

	/**
	 * Compute the tone value for a particular point in the sample
	 * 
	 * @param sample
	 *            is the index of the sample in the array (represents how far
	 *            through the signal we are)
	 * @return
	 */
	@Override
	protected double compute(int sample) {
		double v = Math.sin((double) sample * 2.0 * Math.PI
				* (double) frequency
				/ (double) AudioSequence.SAMPLES_PER_SECOND);
		return v;
	}

}
