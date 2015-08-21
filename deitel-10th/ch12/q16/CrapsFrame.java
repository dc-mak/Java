import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;

public final class CrapsFrame extends JFrame {
	private final Craps game = new Craps();
	private final Status state = Status.LOST;

	private final String ROLL_D = "Roll Dice";
	private final JButton roll = new JButton(ROLL_D);

	private final JLabel DIE1   = new JLabel("Die 1: ");
	private final JLabel DIE2   = new JLabel("Die 2: ");
	private final JLabel SUM    = new JLabel("Sum: ");
	private final JLabel POINT = new JLabel("Point: ");

	private final JTextField die1  = new JTextField();
	private final JTextField die2  = new JTextField();
	private final JTextField sum   = new JTextField();
	private final JTextField point = new JTextField();

	public CrapsFrame() {
		super("Craps");
		setLayout(new BorderLayout());
		final JPanel info = new JPanel(new GridLayout(4, 2, 5, 5));
		info.add(DIE1);
		info.add(die1);
		info.add(DIE2);
		info.add(die2);
		info.add(SUM);
		info.add(sum);
		info.add(POINT);
		info.add(point);
		add(roll, BorderLayout.NORTH);
		add(info, BorderLayout.CENTER);
		
		roll.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				roll.setText(ROLL_D);
				game.nextTurn();

				die1.setText(Integer.toString(game.getDie1()));
				die2.setText(Integer.toString(game.getDie2()));
				sum.setText(Integer.toString(game.getSum()));
				point.setText(Integer.toString(game.getPoint()));
				
				if (game.getStatus() == Status.WON ||
					game.getStatus() == Status.LOST) {
					game.reset();
					roll.setText("You "+game.getStatus()+". Reset?");
				}
			}});
	}
}
					
