// GUI Ex 10: TestDraw.java for inherited classes.

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TestDraw
{
   public static void main(String[] args)
   {
	  int i = Integer.parseInt(
			     JOptionPane.showInputDialog(
					 "How many shapes do you want?"));
	  while (i < 1) {
		  i = Integer.parseInt(
			     JOptionPane.showInputDialog(
					 "Number must be 1 or greater."));
	  }

	  DrawPanel panel = new DrawPanel(i);
      JFrame app = new JFrame();
	  JLabel count = new JLabel(
		 String.format("Lines: %d, Rectangles: %d, Ovals: %d",
			panel.getLinesNum(), panel.getRectsNum(), panel.getOvalsNum()));

      app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      app.add(panel);
	  app.add(count, BorderLayout.SOUTH);
      app.setSize(300, 300);
      app.setVisible(true);
   } 
} // end class TestDraw


/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
