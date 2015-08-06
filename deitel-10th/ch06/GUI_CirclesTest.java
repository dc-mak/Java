/**
 * Created by Dhruv on 31/07/2015.
 */

import javax.swing.JFrame;

public class GUI_CirclesTest {
    public static void main(String[] args) {
        GUI_Circles panel = new GUI_Circles();
        JFrame app = new JFrame();

        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.add(panel);
        app.setSize(600, 600);
        app.setVisible(true);
    }
}
