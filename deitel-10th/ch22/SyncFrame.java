// Ex 22.10: Dummy pannel that is drawn on.

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public final class SyncFrame extends JFrame {
	private final SyncPanel sync  = new SyncPanel();
	private final JSlider slider  = new JSlider(SwingConstants.HORIZONTAL, -1000, 1000, 0);
	private final JTextField text = new JTextField(Integer.toString(slider.getValue()));

	public SyncFrame() {
		super("Syncing Text Field and Slider");
		add(BorderLayout.NORTH, text);
		add(sync);
		add(BorderLayout.SOUTH, slider);

		text.setFont(new Font("SansSerif", Font.BOLD, 20));

		slider.addChangeListener(
			e -> {sync.setText(slider.getValue());
				  System.out.println("slider");
				  text.setText(Integer.toString(slider.getValue()));});

		text.addActionListener( e -> {
			try { slider.setValue(Integer.parseInt(text.getText()));
					System.out.println("text");
			} catch (NumberFormatException nfe) { /* suppress */ }});

		sync.setText(slider.getValue());
	}

	public static void main(String[] args){
		SyncFrame app = new SyncFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(300, 200);
		app.setVisible(true);
	}
}
