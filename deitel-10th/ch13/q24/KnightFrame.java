import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;

public final class KnightFrame extends JFrame {
	private final JLabel status = new JLabel();
	private final KnightPanel board = new KnightPanel(status);

	public KnightFrame() {
		super("Knight's Tour");
		add(status, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
	}
}
