// Ex 12.8: Recreate give GUI.

import javax.swing.JFrame;

public class Align {
    public static void main(String[] args) {
        AlignFrame app = new AlignFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(350,150);
        app.setVisible(true);
    }
}
