// GUI Exercise for Chapter 10: Inheritance.

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private final MyShape[]	shapes;
	private final int[] total_shapes = new int[3];

	public int getLinesNum() { return total_shapes[0]; }
	public int getRectsNum() { return total_shapes[1]; }
	public int getOvalsNum() { return total_shapes[2]; }

	public DrawPanel(int shapes_num) {

		Random rand = new Random();
		setBackground(Color.WHITE);
		shapes = new MyShape[shapes_num];
		Arrays.fill(total_shapes, 0);

		for (int count = 0; count < shapes.length; count++) {

			int x1 = rand.nextInt(300);
			int y1 = rand.nextInt(300);
			int x2 = rand.nextInt(300);
			int y2 = rand.nextInt(300);

			Color color = new Color(rand.nextInt(256), 
					rand.nextInt(256), rand.nextInt(256));

			total_shapes[x1 % 3]++;

			if (x1 % 3 == 0)
				shapes[count] = new MyLine(x1, y1, x2, y2, color);
			else if (x1 % 3 == 1)
				shapes[count] = new MyRectangle(x1,y1,x2,y2,color, x2 % 2 == 0);
			else
				shapes[count] = new MyOval(x1,y1,x2,y2,color, x2 % 2 == 0);

		} 

	} 


	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (MyShape shape : shapes)
			shape.draw(g);
	} 
}
