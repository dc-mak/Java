// GUI Exercise for Chapter 8

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private Random rand = new Random();   
	private MyLine[]	   lines;
	private MyRectangle[]  rects;
	private MyOval[]	   ovals;

	public DrawPanel() {
		setBackground(Color.WHITE);

		lines = new MyLine[1 + rand.nextInt(5)];
		rects = new MyRectangle[1 + rand.nextInt(5)];
		ovals = new MyOval[1 + rand.nextInt(5)];

		for (int count = 0; count < lines.length; count++) {

			int x1 = rand.nextInt(300);
			int y1 = rand.nextInt(300);
			int x2 = rand.nextInt(300);
			int y2 = rand.nextInt(300);


			Color color = new Color(rand.nextInt(256), 
					rand.nextInt(256), rand.nextInt(256));


			lines[count] = new MyLine(x1, y1, x2, y2, color);
		} 

		for (int count = 0; count < rects.length; count++) {

			int x1 = rand.nextInt(300);
			int y1 = rand.nextInt(300);
			int x2 = rand.nextInt(300);
			int y2 = rand.nextInt(300);
			boolean filled = rand.nextInt(2) == 0;

			Color color = new Color(rand.nextInt(256), 
					rand.nextInt(256), rand.nextInt(256));


			rects[count] = new MyRectangle(x1, y1, x2, y2, color, filled);
		} 

		for (int count = 0; count < ovals.length; count++) {

			int x1 = rand.nextInt(300);
			int y1 = rand.nextInt(300);
			int x2 = rand.nextInt(300);
			int y2 = rand.nextInt(300);
			boolean filled = rand.nextInt(2) == 0;

			Color color = new Color(rand.nextInt(256), 
					rand.nextInt(256), rand.nextInt(256));
			ovals[count] = new MyOval(x1, y1, x2, y2, color, filled);
		} 
	} 


	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (MyLine line : lines)
			line.draw(g);

		for (MyRectangle rect : rects)
			rect.draw(g);

		for (MyOval oval : ovals)
			oval.draw(g);
	} 
}
