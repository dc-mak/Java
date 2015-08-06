// Ex 4.1)a) Using drawLine to draw lines from the top-left corner to the
//			 opposite main diagonal.
//        b) Using drawLine to draw lines from the all corners to the
//			 opposite main diagonal.

import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
   // draws a line from top-left corner of the panel
   public void paintComponent(Graphics g) {
      // call paintComponent to ensure the panel displays correctly
      super.paintComponent(g);

	  // dimensions
	  int width  = getWidth();
	  int height = getHeight();

	  // increments
	  int div = 15;
      int dx = width / div;
      int dy = height / div;

	  // From bottom-left to top-right(/ paired with top-left)
	  int x_BL = 0;
	  int y_BL = height;

	  // From bottom-right to top-left(\ paired with top-right)
	  int x_BR = width;
	  int y_BR = height;

	  int step = 1;
	  while (step <= div) {
		  // From bottom-left to top-right(/ paired with top-left)
		  g.drawLine(0, 0, x_BL, y_BL);
		  g.drawLine(x_BL, y_BL, width, height); // join to bottom-right
		  x_BL += dx;
		  y_BL -= dy;

		  // From bottom-right to top-left(\ paired with top-right)
		  g.drawLine(width, 0, x_BR, y_BR);
		  g.drawLine(x_BR, y_BR, 0, height); // join to bottom-left
		  x_BR -= dx;
		  y_BR -= dy;

		  // DON'T FORGET THE INCREMENT
		  step++;
	  }
   }
}
