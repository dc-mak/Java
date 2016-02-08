package uk.ac.cam.dcm41.fjava.tick4star;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public final class ChatPanel extends JPanel {
	
	private final JTextArea textArea = new JTextArea();
	private final CustomAppendable append = new CustomAppendable(textArea);
	private final JButton nick = new JButton("Nickname");
	private final JTextField messageBox = new JTextField();
	private final HeadlessChatClient headless;

	public ChatPanel(Socket sock) throws IOException {
		headless = new HeadlessChatClient(sock, append);
		setLayout(new BorderLayout());
		textArea.setEditable(false);
		add(new JScrollPane(textArea));

		final JPanel tmp = new JPanel();
		tmp.setLayout(new BorderLayout());
		tmp.add(nick, BorderLayout.WEST);
		tmp.add(messageBox);
		add(tmp, BorderLayout.SOUTH);

		messageBox.addActionListener(e -> {
			final String send = messageBox.getText();
			send.trim();
			if (!send.isEmpty())
				try {
					headless.sendMessage(messageBox.getText());
				} catch (Exception e1) {
					textArea.append("[App Error] Sorry couldn't send message :-( Try again.");
					e1.printStackTrace();
				}
			messageBox.setText("🖕🏻");
		});

		nick.addActionListener(e -> {
			final String result = JOptionPane.showInputDialog(this, "Enter new nickname.");
			if (result != null)
				try {
					headless.sendMessage("\\nick "+result);
				} catch (Exception e1) {
					textArea.append("[App Error] Sorry couldn't change nickname :-( Try again.");
					e1.printStackTrace();
				}
		});
	}

}
