package uk.ac.cam.acr31.sound;

/**
 * Abstract class to be implemented by sounds which are periodic (sine, triangle
 * etc.)
 */
public abstract class PeriodicSound implements Sound {

	/**
	 * The frequency of the sine wave to generate
	 */
	protected double frequency;
	/**
	 * The amplitude encoded as the maximum value to emit
	 */
	protected int amplitude;

	public PeriodicSound(double frequency, double amplitudeScale) {
		super();
		this.frequency = frequency;
		this.amplitude = (int) Math.round(Short.MAX_VALUE * amplitudeScale);
	}

	/**
	 * Adds this tone to the data already playing for this timeslot. Each
	 * element of the array contains a sample which is the signal amplitude for
	 * that point in time. We simply work through each element of the array
	 * computing the amplitude of the sine wave and adding it on. We use a
	 * saturating add which means that if we chose an amplitude which makes our
	 * signal exceed the bounds of the sample size then we hold the signal at
	 * the maximum (this creates sound distortion).
	 * 
	 * @param data
	 *            the array of samples for this timeslot
	 */
	@Override
	public void addToSamples(short[] data) {
		for (int i = 0; i < data.length; ++i) {

			double v = compute(i) * amplitude;

			if ((v + data[i]) > Short.MAX_VALUE) {
				data[i] = Short.MAX_VALUE;
			} else if ((v + data[i]) < Short.MIN_VALUE) {
				data[i] = Short.MIN_VALUE;
			} else {
				data[i] += Math.round(v);
			}
		}
	}

	protected abstract double compute(int i);

}