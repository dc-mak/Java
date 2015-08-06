package uk.ac.cam.acr31.sound;

/**
 * This implements a low pass filter which removes all frequency components
 * above the chosen frequency. Its implemented with a (naive) rectangular
 * windowed 7th order FIR filter.
 */
public class RectangularLowPassFilter implements Sound {
	private static final int ORDER = 7;
	private Sound snd;
	private double coeff[] = new double[ORDER + 1];

	public RectangularLowPassFilter(Sound snd, double freq) {
		this.snd = snd;
		// Compute truncated sinc, and sample
		double t = -(ORDER + 1) / 2;
		for (int i = 0; i < ORDER + 1; i++) {
			double samp = 2 * Math.PI * freq * t
					/ (double) (AudioSequence.SAMPLES_PER_SECOND);
			if (samp != 0) {
				coeff[i] = 2 * (freq / AudioSequence.SAMPLES_PER_SECOND)
						* Math.sin(samp) / samp;
			} else {
				// Definition at 0
				coeff[i] = 2 * (freq / AudioSequence.SAMPLES_PER_SECOND);
			}
			t++;
		}

	}

	@Override
	public void addToSamples(short[] data) {
		// The original data
		short[] soundData = new short[data.length];
		// Call the relevant sound to get data
		snd.addToSamples(soundData);

		int i, j;
		// Cheap and nasty "convolution", ignoring symmetry
		for (i = 0; i < data.length; i++) {
			for (j = 0; j < ORDER + 1; j++) {
				if (i - j > 0) {
					data[i] += coeff[j] * soundData[i - j];
				}
			}
		}
	}

}
