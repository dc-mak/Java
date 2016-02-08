package uk.ac.cam.dcm41.fjava.tick1;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class StringReceive {

	public static void main(String[] args) {

		// Insufficient number of arguments
		final String twoArgs = "This application requires two arguments: <machine> <port>";
		if (args.length < 2) {
			System.err.println(twoArgs);
			return;
		}

		// Invalid arguments
		final String server = args[0];
		int port = 0;
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

		try (final Socket conn = new Socket(server, port)) {

			final InputStream input = conn.getInputStream();

			// Set up
			final int buffSize  = 1024;
			int readVal = 0;
			final byte[] buffer = new byte[buffSize];

			// Perpetual loop
			while (true) {
				readVal = input.read(buffer, 0, buffSize);
				if (readVal != -1 && readVal < buffSize) {
					System.out.print(new String(buffer, 0, readVal));
				}
			}

		} catch (UnknownHostException uhe) {
			System.err.printf("Cannot connect to %s on port %d%n", server, port);
			return;
		} catch (IOException ioe){
			System.err.printf("Cannot connect to %s on port %d%n", server, port);
			return;
		}
	}
}
