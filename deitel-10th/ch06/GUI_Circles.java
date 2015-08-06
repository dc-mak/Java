/**
 * GUI Ex 6.01: Draw concentric circles with randomised colours.
 * Created by Dhruv on 31/07/2015.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GUI_Circles extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int height = getHeight();
        int width  = getWidth();

        // Centre
        int thick = 40;
        int circles = 5;
        int y = height/2 - thick * (circles + 1);
        int x = width/2 - thick * (circles + 1);

        Random rand = new Random ();
        Color col1 = new Color (rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        Color col2 = new Color (rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

        for (int i = circles; i > 0; i--) {
            if (i % 2 == 1)
                g.setColor(col1);
            else
                g.setColor(col2);
            g.fillOval(x, y, 2 * thick * (i + 1), 2 * thick * (i + 1));
            x += thick;
            y += thick;
        }
    }
}
