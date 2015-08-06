// Ex 4.2)a) Using drawLine to draw given pattern.
//        b) Using drawLine to draw given patten from all 4 corners.

import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel2 extends JPanel {
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

	  int y = 0;
	  int x = dx;

	  int step = 1;
	  // Stay with me, it simply goes aroung in a circle.
	  while (step <= div) {
		  // From (top-left to bottom-left) to(bottom-left to bottom-right)
		  g.drawLine(0, y, x, height);
		  // From (bottom-left to bottom-right) to(bottom-right to top-right)
		  g.drawLine(x-dx, height, width, height-y-dy);
		  // From (bottom-right to top-right) to(top-right to top-left)
		  g.drawLine(width, height-y, width-x, 0);
		  // From (top-right to top-left) to(top-left to bottom-left)
		  g.drawLine(width-x+dx, 0, 0, y+dy);

		  y += dy;
		  x += dx;

		  // DON'T FORGET THE INCREMENT
		  step++;
	  }
   }
}
