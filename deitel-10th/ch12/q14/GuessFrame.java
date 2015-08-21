// Ex 12.14: Basic number guessing game.

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public final class GuessFrame extends JFrame {
	private final String firstGuess = "First guess: ";
	private final Color warmer  = Color.RED;
	private final Color colder  = Color.CYAN;
	private final Color correct = Color.GREEN;

	private int NUM;
	private int prev = 0;

	private final JLabel prompt    = new JLabel();
	private final JTextField input = new JTextField(firstGuess, 10);
	private final JButton reset    = new JButton("Reset");

	public GuessFrame() {
		super("Guess-the-Number Game");
		setLayout(new GridLayout(1, 3, 2, 2));
		add(prompt);
		add(input);
		add(reset);
	
		final Random rand = new Random(5);
		NUM = 1 + rand.nextInt(1000);

		input.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				String hint = "Please enter a number.";
				try {
					final int guess = Integer.parseInt(input.getText());
					final int close = Math.abs(guess-NUM);
					getContentPane().setBackground(prev > close ?  warmer : colder);
					prev = close;
					input.setText("");
					if (guess < NUM) {
						hint = "Too low"+" ("+guess+")";
					} else if (guess > NUM) {
						hint = "Too high"+" ("+guess+")";
					} else /* guess == NUM */ {
						hint = "Correct!";
						getContentPane().setBackground(Color.GREEN);
						input.setEditable(false);
					}
				} catch (NumberFormatException nfe) { /* suppress */ }
				prompt.setText(hint);
			}});

		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				prev = 0;
				NUM  = 1+rand.nextInt(1000);
				getContentPane().setBackground(Color.WHITE);
				input.setText(firstGuess);
				prompt.setText("");
				input.setEditable(true);
			}});
	}
}
