// GUI Exercise for Chapter 9

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private final Random rand = new Random();   
	private final MyLine[]	   lines;
	private final MyRectangle[]  rects;
	private final MyOval[]	   ovals;
	private final int lines_num;
	private final int rects_num;
	private final int ovals_num;

	public int getLinesNum() { return lines_num; }
	public int getRectsNum() { return rects_num; }
	public int getOvalsNum() { return ovals_num; }

	public DrawPanel() {
		setBackground(Color.WHITE);

		lines_num = 1 + rand.nextInt(5);
		rects_num = 1 + rand.nextInt(5);
		ovals_num = 1 + rand.nextInt(5);

		lines = new MyLine[lines_num];
		rects = new MyRectangle[rects_num];
		ovals = new MyOval[ovals_num];

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
