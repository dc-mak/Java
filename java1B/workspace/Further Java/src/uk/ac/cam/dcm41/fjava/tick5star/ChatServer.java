package uk.ac.cam.dcm41.fjava.tick5star;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import uk.ac.cam.cl.fjava.messages.Message;

public class ChatServer {
	public static void main(final String[] args) throws ClassNotFoundException, SQLException {

		final String msg = "Usage: ChatServer <dbpath> <client> <fed> [fedsrv1:port fedsrv2:port ...]";

		if (args.length < 3) {
			System.err.println(msg);
			return;
		}

		final int port;
		try {
			port = Integer.parseInt(args[1]);
		} catch (final NumberFormatException nfe) {
			System.err.println(msg);
			return;
		}
		
		final int fedPort;
		try {
			fedPort = Integer.parseInt(args[2]);
		} catch (final NumberFormatException nfe) {
			System.err.println(msg);
			return;
		}

		final ServerSocket serverSoc;
		try {
			serverSoc = new ServerSocket(port);
		} catch (final IOException ioe) {
			System.err.println("Cannot use port number " + port);
			return;
		}
		
		final ServerSocket externalSoc;
		try {
			externalSoc = new ServerSocket(fedPort);
		} catch (IOException e) {
			System.err.println("Cannot use port number " + port);

			if (serverSoc != null)
				try {
					serverSoc.close();
				} catch (final IOException ioe2) {
					System.err.println("[ERROR] Closing server");
					ioe2.printStackTrace(System.err);
				}

			return;
		}

		final MultiQueue<Message> mq = new MultiQueue<>();
		final CustomMessageSet set = new CustomMessageSet(mq);

		for (int i = 3; i < args.length; i++) {
			try {

				final String[] hostPort = args[i].split(":");
				if (hostPort.length != 2) {
					System.err.printf("Warning: cannot interpret '%s' as 'fedsrv:port'. Ignoring.", args[i]);
					continue;
				}
				new FederatedHandler(new Socket(hostPort[0], Integer.parseInt(hostPort[1])), set);

			} catch (final IOException ioe) {
				System.err.printf("Warning: Cannot connect to '%s'. Ignoring.\n", args[i]);
			} catch (final NumberFormatException nfe) {
				System.err.printf("Warning: cannot interpret '%s' as 'fedsrv:port'. Ignoring.", args[i]);
			}
		}

		final FederatedListener fedListen = new FederatedListener(externalSoc, set);
		fedListen.setDaemon(true);
		fedListen.start();

		try {

			while (true) {

				new ClientHandler(serverSoc.accept(), mq, new Database(args[0]));

			}

		} catch (final IOException ioe) {

			System.err.println("Warning: cannot interpret '" + args[0] + "' as 'fedsrv:port'. Ignoring.");
			ioe.printStackTrace(System.err);

		} finally {

				if (serverSoc != null)
					try {
						serverSoc.close();
					} catch (final IOException ioe) {
						System.err.println("[ERROR] Closing server");
						ioe.printStackTrace(System.err);
					}

				if (externalSoc != null)
					try {
						externalSoc.close();
					} catch (final IOException ioe) {
						System.err.println("[ERROR] Closing server");
						ioe.printStackTrace(System.err);
					}

		}
	}
}
