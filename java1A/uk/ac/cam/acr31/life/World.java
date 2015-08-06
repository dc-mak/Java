package uk.ac.cam.acr31.life;

import java.awt.Graphics;
import java.io.Writer;

public interface World {

	public void setCell(int x, int y, boolean live);

	public boolean getCell(int x,int y);
	
	public int getWidth();

	public int getHeight();
		
	public int getGeneration();
	
	public int getPopulation();
	
	public void print(Writer w);
	
	public void draw(Graphics g,int width, int height);
	
	public World nextGeneration(int log2StepSize);
}
