package uk.ac.cam.acr31.sound;


/**
 * This class implements an ADSR Envelope. ADSR stands for Attack, Decay,
 * Sustain, Release. You apply it as a filter on top of an existing sound and it
 * will reshape the waveform.
 * <p>
 * The idea is that the signal has an <b>attack</b> period at the beginning
 * where it ramps up, then you have <b>decay</b> down from the attack until you
 * reach the <b>sustain</b> level, this is then held until the <b>release</b>
 * time at which point we decay back to zero
 * <ul>
 * <li>Attack is the proportion of the time to spend ramping up the signal from
 * zero</li>
 * <li>Decay is the the proportion of time to spend decaying down from the
 * attack to the sustain level</li>
 * <li>Sustain level is the level to hold the signal at</li>
 * <li>Release is the proportion of time into the signal to begin decaying the
 * sustain level back to 0</li>
 * </ul>
 * 
 * @see http://en.wikipedia.org/wiki/Synthesizer#ADSR_envelope
 */
public class ADSREnvelopeFilter implements Sound {

	/**
	 * Initial sound to apply the filter over
	 */
	private Sound snd;

	/**
	 * The point in the sound (as a proportion) to end ramping up the initial
	 * attack
	 */
	private double attack;

	/**
	 * The point in the sound (as a proportion) to end decaying the attack back
	 * to the sustain level
	 */
	private double decay;

	/**
	 * The sustain level
	 */
	private double sustainLevel;

	/**
	 * The point in the sound (as a propotion) to being decaying the sustain
	 * level back to 0
	 */
	private double release;

	/**
	 * The length of the final envelope (values less than one will result in a
	 * shortened sound which ends with silence
	 */
	private double duration = 1f;

	public ADSREnvelopeFilter(Sound snd, double attack, double decay,
			double sustainLevel, double release, double duration) {
		this.attack = attack;
		this.decay = decay;
		this.sustainLevel = sustainLevel;
		this.release = release;
		this.snd = snd;
		this.duration = duration;
	}

	public ADSREnvelopeFilter(Sound snd, double attack, double decay,
			double sustainLevel, double release) {
		super();
		this.attack = attack;
		this.decay = decay;
		this.sustainLevel = sustainLevel;
		this.release = release;
		this.snd = snd;
	}

	@Override
	public void addToSamples(short[] data) {
		int i;
		int t = 0; // Time in samples
		// Create our own empty copy of data for waveshaping
		short[] soundData = new short[data.length];
		// Call the relevant sound to get data
		snd.addToSamples(soundData);

		// And the full duration of our note in samples
		int dsamp = (int) (data.length * duration);

		try {
			// Shape envelope
			// Attack
			for (i = 0; i < (dsamp * attack); i++) {
				soundData[t] *= i / (dsamp * attack);
				t++;
			}
			// Decay down to sustain level
			for (i = 0; i < (dsamp * decay); i++) {
				double tr = 1 + (i * (sustainLevel - 1f) / (dsamp * decay));
				soundData[t] *= tr;
				t++;
			}

			// Sustain for that amount
			for (i = 0; i < (dsamp * (1 - (attack + decay + release))); i++) {
				soundData[t] *= sustainLevel;
				t++;
			}

			// Compute release
			for (i = 0; i < (dsamp * release); i++) {
				double tr = sustainLevel
						+ ((-sustainLevel * i) / (dsamp * release));
				soundData[t] *= tr;
				t++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// Do absolutely nothing, due to potentially having missed the last
			// sample
			// due to floats
		}

		// Add back to data
		// Saturating add
		for (i = 0; i < dsamp; i++) {
			if ((soundData[i] + data[i]) > Short.MAX_VALUE) {
				data[i] = Short.MAX_VALUE;
			} else if ((soundData[i] + data[i]) < Short.MIN_VALUE) {
				data[i] = Short.MIN_VALUE;
			} else {
				data[i] += Math.round(soundData[i]);
			}
		}

	}

}
