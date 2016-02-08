package uk.ac.cam.dcm41.fjava.tick1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class StringChat {
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

		// 'S' is declared final because it is not expected/intended to be changed 
		try (final Socket sock = new Socket(server, port)) {
			// Set up for input from network
			final InputStream in = sock.getInputStream();
			
			// Output to screen thread
			final Thread output = new Thread() {
				@Override
				public void run() {

					final int buffSize = 1024;
					int readVal = 0;
					final byte[] buffer = new byte[buffSize];
					
					try {
						
						// Perpetual loop
						while (true) {
							readVal = in.read(buffer, 0, buffSize);
							if (readVal != -1 && readVal < buffSize) {
								System.out.println(new String(buffer, 0, readVal));
							}
						}

					} catch (IOException ioe) {
						System.err.println("IO Exception: Restart application");
						return;
					}
				}};

			// Daemon thread means that it doesn't stop the JVM from closing.
			// This also has the implication that Daemon threads are abandoned,
			// finally blocks are not executed and stacks are not unwound.
			output.setDaemon(true);
			output.start();

			// Output to network thread
			final OutputStream serverOut = sock.getOutputStream();
			BufferedReader r = new BufferedReader(new InputStreamReader(System.in), 40);

			while(true) {
				serverOut.write(r.readLine().getBytes());
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
