// Ex 12.9: Recreate given GUI.

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class CalcFrame extends JFrame {
    private final JTextField display;

    private final JButton seven;
    private final JButton eight;
    private final JButton nine;
    private final JButton divide;

    private final JButton four;
    private final JButton five;
    private final JButton six;
    private final JButton times;

    private final JButton one;
    private final JButton two;
    private final JButton three;
    private final JButton minus;

    private final JButton zero;
    private final JButton dot;
    private final JButton equals;
    private final JButton plus;

    public CalcFrame() {
        super("Calculator");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setResizable(false);

        final display = new JTextField(16);
        add(display);

        seven  = new JButton("7");
        eight  = new JButton("8");
        nine   = new JButton("9");
        divide = new JButton("/");
        four   = new JButton("4");
        five   = new JButton("5");
        six    = new JButton("6");
        times  = new JButton("*");
        one    = new JButton("`");
        two    = new JButton("2");
        three  = new JButton("3");
        minus  = new JButton("-");
        zero   = new JButton("0");
        dot    = new JButton(".");
        equals = new JButton("=");
        plus   = new JButton("+");

        final JPanel keypad = new JPanel(new GridLayout(5,5,5,5));

		keypad.add(seven);
		keypad.add(eight);
		keypad.add(nine);
		keypad.add(divide);
		keypad.add(four);
		keypad.add(five);
		keypad.add(six);
		keypad.add(times);
		keypad.add(one);
		keypad.add(two);
		keypad.add(three);
		keypad.add(minus);
		keypad.add(zero);
		keypad.add(dot);
		keypad.add(equals);
		keypad.add(plus);

        add(keypad);
    }
}
