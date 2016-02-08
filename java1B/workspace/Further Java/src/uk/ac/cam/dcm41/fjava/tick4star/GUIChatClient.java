package uk.ac.cam.dcm41.fjava.tick4star;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import uk.ac.cam.cl.fjava.messages.Execute;
import uk.ac.cam.cl.fjava.messages.Message;

@SuppressWarnings("serial")
public final class GUIChatClient extends Message {
	
	private final String server;
	private final int port;

	public GUIChatClient(final String server, final int port) {
		this.server = server;
		this.port = port;
	}

	@Execute
	public void startGUIChatClient() {
		try {

			final JFrame app = new JFrame("GUI Chat Client");
			app.add(new ChatPanel(new Socket(server, port)));
			app.setSize(400, 420);
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.setVisible(true);

		} catch (UnknownHostException uhe) {
			System.err.printf("Cannot connect to %s on port %d%n", server, port);
			return;
		} catch (IOException ioe) {
			System.err.printf("Cannot connect to %s on port %d%n", server, port);
			return;
		}
	}

}
