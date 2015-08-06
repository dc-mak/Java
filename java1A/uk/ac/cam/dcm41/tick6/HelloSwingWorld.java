package uk.ac.cam.dcm41.tick6;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloSwingWorld extends JFrame {
 HelloSwingWorld() {
  super("Hello Swing");                    //create window & set title text
  setDefaultCloseOperation(EXIT_ON_CLOSE); //close button on window quits app.
  JLabel text = new JLabel("Hello Swing"); //create graphical text label
  add(text);                               //associate "text" with window
  setSize(320,240);                        //set size of window
 }
 public static void main(String[] args) {
  HelloSwingWorld hello = new HelloSwingWorld(); //create instance 
  hello.setVisible(true);                        //display window to user
 }
}
