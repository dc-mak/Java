// Ex 12.9: Recreate given GUI.

import javax.swing.JFrame;

public final class Calc {
    public static void main(String[] args) {
        CalcFrame app = new CalcFrame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(195, 185);
        app.setVisible(true);
    }
}
