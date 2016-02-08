package uk.ac.cam.dcm41.fjava.tick4star;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public final class TextChatClient {

	static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		String server = null;
		int port = 0;

		// Insufficient number of arguments
		final String twoArgs = "This application requires two arguments: <machine> <port>";
		if (args.length < 2) {
			System.err.println(twoArgs);
			return;
		}

		server = args[0];
		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException nfe) {
			System.err.println(twoArgs);
			return;
		}

		if (port < 0 || 65_535 < port) {
			System.err.println("Port number must be between 0 and 65,535 inclusive.");
			return;
		}

		try (final Socket sock = new Socket(server, port);
			 final Scanner clientIn = new Scanner(System.in)) {

			final HeadlessChatClient headless = new HeadlessChatClient(sock, System.out);

			while (true) {
				try {
					headless.sendMessage(clientIn.nextLine());
				} catch (IOException ioe) {
					System.err.println("Error sending message. Please try again.");
				}
			}

		} catch (UnknownHostException uhe) {
			System.err.printf("Cannot connect to %s on port %d%n", server, port);
			return;
		} catch (IOException ioe) {
			System.err.printf("Cannot connect to %s on port %d%n", server, port);
			return;
		}
	}
}
