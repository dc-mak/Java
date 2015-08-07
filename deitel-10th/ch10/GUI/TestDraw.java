// GUI Ex for Chapter 10: Inheritance.

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TestDraw {
   public static void main(String[] args) {
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
} 
