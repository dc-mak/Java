package uk.ac.cam.dcm41.fjava.tick4;

import java.io.IOException;
import java.net.ServerSocket;

import uk.ac.cam.cl.fjava.messages.Message;

public class ChatServer {
	public static void main(final String[] args) {

		final String msg = "Usage: java ChatServer <port>";

		if (args.length != 1) {
			System.err.println(msg);
			return;
		}

		final int port;
		try {
			port = Integer.parseInt(args[0]);
		} catch (final NumberFormatException nfe) {
			System.err.println(msg);
			return;
		}

		final ServerSocket serverSoc;
		try {
			serverSoc = new ServerSocket(port);
		} catch (final IOException e) {
			System.err.println("Cannot use port number " + port);
			return;
		}

		final MultiQueue<Message> mq = new MultiQueue<>();

		try {

			while (true) {

				new ClientHandler(serverSoc.accept(), mq);

			}

		} catch (final IOException e) {

			System.err.println("[ERROR] Unexpected server error");
			e.printStackTrace(System.err);

		} finally {

			if (serverSoc != null)
				try {
					serverSoc.close();
				} catch (final IOException e) {
					System.err.println("[ERROR] Closing server");
					e.printStackTrace(System.err);
				}

		}
	}
}
