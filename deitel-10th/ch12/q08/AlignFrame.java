// Ex 12.8: Recreate give GUI.

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlignFrame extends JFrame {
    private final JCheckBox snapGrid;
    private final JCheckBox showGrid;

    private final JLabel xLabel;
    private final JLabel yLabel;

    private final JTextField xCoord;
    private final JTextField yCoord;

    private final JButton ok;
    private final JButton cancel;
    private final JButton help;

    public AlignFrame() {
        super("Align");
        setLayout(new FlowLayout(FlowLayout.CENTER));

        final Box checks  = Box.createVerticalBox();
        final Box coords  = Box.createVerticalBox();
        final JPanel buttons = new JPanel();

        snapGrid = new JCheckBox("Snap to Grid");
        showGrid = new JCheckBox("Show Grid");
        checks.add(snapGrid);
        checks.add(showGrid);

        xLabel = new JLabel("X: ");
        xCoord = new JTextField("8", 3);
        final JPanel xPanel = new JPanel();
        xPanel.add(xLabel);
        xPanel.add(xCoord);

        yLabel = new JLabel("Y: ");
        yCoord = new JTextField("8",3);
        final JPanel yPanel = new JPanel();
        yPanel.add(yLabel);
        yPanel.add(yCoord);

        coords.add(xPanel);
        coords.add(yPanel);

        ok     = new JButton("Ok");
        cancel = new JButton("Cancel");
        help   = new JButton("Help");
        buttons.setLayout(new GridLayout(3, 1, 5, 10));
        buttons.add(ok);
        buttons.add(cancel);
        buttons.add(help);

        add(checks);
        add(coords);
        add(buttons);
    }
}
