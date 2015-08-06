package uk.ac.cam.acr31.sound;

/**
 * Generates a triangular wave with the chosen frequency and amplitude scale. No
 * end-of-sample normalization is done which means that the signal might end
 * somewhere other than a zero crossing. This will cause pops in the sound. Use
 * a filter to fix this.
 * 
 * @see ADSREnvelopeFilter
 * @see RectangularLowPassFilter
 */
public class TriangleWaveSound extends PeriodicSound {

	/**
	 * The duty cycle is the proportion of a period we spend with a rising value
	 */
	private double dutyCycle = 0.5;

	/**
	 * Create a new triangular wave with a 50% duty cycle
	 * 
	 * @param frequency
	 *            of the signal (@see Frequency}
	 * @param amplitudeScale
	 *            should be between 0 and 1 to indicate the amplitude of the
	 *            signal. Warning: if you use 1 here then you'll be using the
	 *            full amplitude range which means that you'll get distortion
	 *            when you combine two sounds together
	 */
	public TriangleWaveSound(double frequency, double amplitudeScale) {
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
	 *            is the proportion of the period which is spent with a rising
	 *            value compared to a value value. A duty cycle of 0.75 means we
	 *            spend three-quarters of the time with a rising value and
	 *            one-quarter with a falling one.
	 */
	public TriangleWaveSound(double frequency, double amplitudeScale,
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
		// Length of one period
		double period = (double) AudioSequence.SAMPLES_PER_SECOND
				/ (double) frequency;
		// How far along we are on one period
		double t = sample % period;
		if (t < (period * dutyCycle)) {
			// Start of cycle
			// Linear ramp up from -1 to 1
			return ((t / (period * dutyCycle)) * 2) - 1;
		} else {
			// Second half of cycle
			return (((t - (period * dutyCycle)) / (period * (1 - dutyCycle))) * -2) + 1;
		}
	}
}
