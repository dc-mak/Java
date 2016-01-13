import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public final class DrawFrame extends JFrame {
	private int count = 1;
	private final ControlPanel controls = new ControlPanel();

	public DrawFrame() {
		super("Drawing");
		setMinimumSize(new Dimension(800, 600));

		// desktops
		final JDesktopPane desktop = new JDesktopPane();
		add(desktop);
		//final InternalDrawFrame tmp = ;
		desktop.add(new InternalDrawFrame(count++));
		add(controls, BorderLayout.WEST);

		// menu
		final JMenuBar bar = new JMenuBar();
		final JMenu file = new JMenu("File");
		final JMenuItem add = new JMenuItem("Add");
		final JMenuItem exit = new JMenuItem("Exit");

		setJMenuBar(bar);
		bar.add(file);
		file.add(add);
		file.addSeparator();
		file.add(exit);

		add.addActionListener(e -> desktop.add(new InternalDrawFrame(count++)));
		exit.addActionListener(e -> System.exit(0));
	}

	// content
	private class InternalDrawFrame extends JInternalFrame {
		private final JLabel status = new JLabel();
		private final DrawPanel draw = new DrawPanel(status);

		public DrawPanel getDrawPanel() { return draw; }

		public InternalDrawFrame(int n) {
			super("Drawing: "+n, true, true, true, true);
			setLayout(new BorderLayout());
			final JPanel tmp = new JPanel(new BorderLayout());
			tmp.add(draw, BorderLayout.CENTER);
			tmp.add(status, BorderLayout.SOUTH);
			add(tmp);
			addInternalFrameListener(new InternalFrameAdapter() {
				@Override
				public void internalFrameActivated(InternalFrameEvent e) {
					controls.setDrawPanel(draw);
				}});
			controls.setDrawPanel(draw);
			setSize(300,300);
			setVisible(true);
		}
	}
}

