package uk.ac.cam.dcm41.fjava.tick4star;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.ac.cam.cl.fjava.messages.ChangeNickMessage;
import uk.ac.cam.cl.fjava.messages.ChatMessage;
import uk.ac.cam.cl.fjava.messages.DynamicObjectInputStream;

public final class HeadlessChatClient {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
	private final ObjectOutputStream clientOut;
	private final HeadlessServerMessages serverOut;
	private final Appendable toApp;

	public HeadlessChatClient(final Socket sock, final Appendable toApp) throws IOException {

		this.toApp = toApp;

		clientOut = new ObjectOutputStream(sock.getOutputStream());

		toApp.append(
			String.format("%s [Client] Connected to %s on port %d.%n",
				DATE_FORMAT.format(new Date()), 
				sock.getInetAddress().getCanonicalHostName(),
				sock.getPort()));

		serverOut =
				new HeadlessServerMessages(
					new DynamicObjectInputStream(sock.getInputStream()), toApp);

		(new Thread(serverOut)).start();

	}
	
	public void sendMessage(final String input) throws IOException {

		if (input.startsWith("\\nick ")) {

			if (input.length() <= 6) {
				toApp.append("Enter \"\\nick\" followed by a space and then the new nickname.");
			} else {
				clientOut.writeObject(new ChangeNickMessage(input.substring(6)));
			}

		} else if (input.startsWith("\\quit")) {

			serverOut.stopMessages();
			toApp.append(String.format("%s [Client] Connection terminated.%n", DATE_FORMAT.format(new Date())));

			// Socket will be closed automatically
			System.exit(0);

		} else {
			
			clientOut.writeObject(new ChatMessage(input));
			
		}
	}

}
