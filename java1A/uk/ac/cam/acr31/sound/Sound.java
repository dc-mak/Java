package uk.ac.cam.acr31.sound;

/**
 * Interface describing a Sound which can be added to an AudioSequence
 * 
 * @author acr31
 */
public interface Sound {

	/**
	 * Adds the audio data for this sound to the data array provided. The array
	 * corresponds to one timeslot of data.
	 * 
	 * @param data
	 */
	public abstract void addToSamples(short[] data);

}