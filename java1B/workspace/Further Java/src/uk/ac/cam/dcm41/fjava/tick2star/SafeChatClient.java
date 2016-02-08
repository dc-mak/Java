package uk.ac.cam.dcm41.fjava.tick2star;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import uk.ac.cam.cl.fjava.messages.ChangeNickMessage;
import uk.ac.cam.cl.fjava.messages.ChatMessage;

@FurtherJavaPreamble(
		author = "Dhruv Makwana",
		date = "25th October 2015",
		crsid = "dcm41",
		summary = "Chat Client extended with different message types.",
		ticker = FurtherJavaPreamble.Ticker.B)

public final class SafeChatClient {

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

		// TODO: Is System.setSecurityManager(new SecurityManager()) needed? what about the code below ?
		// All.policy grants all permissions
		System.setProperty("java.security.policy", "http://www.cl.cam.ac.uk/teaching/1011/FJava/all.policy");
		System.setSecurityManager(new SecurityManager());
	
		try (final Socket sock = new Socket(server, port);
			 final Scanner clientIn = new Scanner(System.in)) {
			// Set up for input from network
			final SafeObjectInputStream serverIn = new SafeObjectInputStream(sock.getInputStream());
			System.out.printf("%s [Client] Connected to %s on port %d.%n", DATE_FORMAT.format(new Date()), server,
					port);
			final ServerMessages serverOut = new ServerMessages(serverIn);
			(new Thread(serverOut)).start();

			// Output to network thread
			final ObjectOutputStream clientOut = new ObjectOutputStream(sock.getOutputStream());

			String input = "[NO INPUT]";
			
			while (true) {

				input = clientIn.nextLine();

				if (input.startsWith("\\nick ")) {

					if (input.length() <= 6) {
						System.out.println("Enter \"\\nick\" followed by a space and then the new nickname.");
					} else {
						clientOut.writeObject(new ChangeNickMessage(input.substring(6)));
					}

				} else if (input.startsWith("\\quit")) {

					serverOut.stopMessages();
					System.out.printf("%s [Client] Connection terminated.%n", DATE_FORMAT.format(new Date()));
					return;

				} else {
					
					clientOut.writeObject(new ChatMessage(input));
					
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
