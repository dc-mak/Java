package uk.ac.cam.acr31.sound;

/**
 * Generates a square wave with the chosen frequency and amplitude scale. No
 * end-of-sample normalization is done which means that the signal might end
 * somewhere other than a zero crossing. This will cause pops in the sound. Use
 * a filter to fix this.
 * 
 * @see ADSREnvelopeFilter
 * @see RectangularLowPassFilter
 */
public class SquareWaveSound extends PeriodicSound {

	/**
	 * The duty cycle is the proportion of the period which we spend with a high
	 * value.
	 */
	private double dutyCycle;

	/**
	 * Create a new square wave with a 50% duty cycle
	 * 
	 * @param frequency
	 *            of the signal (@see Frequency}
	 * @param amplitudeScale
	 *            should be between 0 and 1 to indicate the amplitude of the
	 *            signal. Warning: if you use 1 here then you'll be using the
	 *            full amplitude range which means that you'll get distortion
	 *            when you combine two sounds together
	 */
	public SquareWaveSound(double frequency, double amplitudeScale) {
		super(frequency, amplitudeScale);
		this.dutyCycle = 0.5;
	}

	/**
	 * Create a new square wave
	 * 
	 * @param frequency
	 *            of the signal (@see Frequency}
	 * @param amplitudeScale
	 *            should be between 0 and 1 to indicate the amplitude of the
	 *            signal. Warning: if you use 1 here then you'll be using the
	 *            full amplitude range which means that you'll get distortion
	 *            when you combine two sounds together
	 * @param dutyCycle
	 *            is the proportion of the period which is spent with a high
	 *            value. A duty cycle of 0.75 means we spend three-quarters of
	 *            the time with a high value and one-quarter with a low.
	 */
	public SquareWaveSound(double frequency, double amplitudeScale,
			double dutyCycle) {
		super(frequency, amplitudeScale);
		this.dutyCycle = dutyCycle;
	}

	/**
	 * Compute the tone value for a particular point in the sample
	 * 
	 * @param sample
	 * @return
	 */
	protected double compute(int sample) {
		double period = (double) AudioSequence.SAMPLES_PER_SECOND
				/ (double) frequency;
		if (sample % period < dutyCycle * period) {
			return 1.0f;
		} else {
			return -1.0f;
		}
	}
}
