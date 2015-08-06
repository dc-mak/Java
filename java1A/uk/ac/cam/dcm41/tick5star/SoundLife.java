package uk.ac.cam.dcm41.tick5star;

import java.io.IOException;
import java.io.FileOutputStream;
import java.lang.NumberFormatException;
import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.sound.Sound;
import uk.ac.cam.acr31.sound.AudioSequence;
import uk.ac.cam.acr31.sound.Frequency;
import uk.ac.cam.acr31.sound.TriangleWaveSound;
import uk.ac.cam.acr31.sound.RectangularLowPassFilter;
import uk.ac.cam.acr31.sound.ADSREnvelopeFilter;

public class SoundLife {
	//To generate a very simple 12-bar blues.
	public static Sound generate(int col, int row, boolean alive) {
		col = col%3;
		row = row%12;

		double cellAmp;
		if (!alive) {
			cellAmp = 0.2;
			switch (row) { //Chord I, across bars 1-4, 5-6, 11-12.
				case 0: 
				case 1:
				case 2:
				case 3:
				case 6:
				case 7:
				case 10:
				case 11:
					switch (col) {
						case 0: return new TriangleWaveSound(Frequency.PIANO_A_SHARP_2, cellAmp);
						case 1: return new TriangleWaveSound(Frequency.PIANO_D3, cellAmp);
						case 2: return new TriangleWaveSound(Frequency.PIANO_F3, cellAmp);
					}
				case 4: 
				case 5:
					switch (col) { //Chord IV, across bars 7-8.
						case 0: return new TriangleWaveSound(Frequency.PIANO_D_SHARP_3, cellAmp);
						case 1: return new TriangleWaveSound(Frequency.PIANO_G3, cellAmp);
						case 2: return new TriangleWaveSound(Frequency.PIANO_A_SHARP_3, cellAmp);
					}
				case 8: 
					switch (col) { //Chord VI, bar 9.
						case 0: return new TriangleWaveSound(Frequency.PIANO_G3, cellAmp);
						case 1: return new TriangleWaveSound(Frequency.PIANO_A_SHARP_3, cellAmp);
						case 2: return new TriangleWaveSound(Frequency.PIANO_C4, cellAmp);
					}
				case 9:
					switch (col) { //Chord V, bar 10.
						case 0: return new TriangleWaveSound(Frequency.PIANO_F3, cellAmp);
						case 1: return new TriangleWaveSound(Frequency.PIANO_A3, cellAmp);
						case 2: return new TriangleWaveSound(Frequency.PIANO_C4, cellAmp);
					}
			}
		} else { //Live cells stick out.
			cellAmp = 0.6;
			return new TriangleWaveSound(Frequency.PIANO_A_SHARP_6, 0.8);
		}
		//To make compiler happy
		return new TriangleWaveSound(Frequency.PIANO_F_SHARP_6, 0.8);
	}

	public static void toSound(World world, int gens, String filename) throws Exception {
		AudioSequence as = new AudioSequence(0.12);

		for (int g = 0; g < gens; g++) {
			for (int r = 0; r < world.getHeight(); r++) {	
				for (int c = 0; c < world.getWidth(); c++) {
					as.advance();
					as.addSound(generate(c, r, world.getCell(c, r)));
				}
			}
			world = world.nextGeneration(0);
		}
		as.write(new FileOutputStream(filename));
	}
	
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.out.println("Error: too few arguments [pattern] [generations] [output filename]");
			return;
		}
	   	try {
			Pattern p = new Pattern(args[0]);
			int gens = Integer.parseInt(args[1]);
			if (gens < 1) throw new NumberFormatException();
			String filename = args[2];

			World world = new ArrayWorld(p.getWidth(), p.getHeight());
			p.initialise(world);
			toSound(world, gens, filename);
		} catch (PatternFormatException pfe) {
			System.out.print(pfe.getMessage());
			return;
		} catch (NumberFormatException nfe) {
			System.out.println("Error: second argument is not a positive integer.");
			return;
		} catch  (IOException ioe) {
			System.out.println("Error: file handling malfunction");
			return;
		}
	}
}
