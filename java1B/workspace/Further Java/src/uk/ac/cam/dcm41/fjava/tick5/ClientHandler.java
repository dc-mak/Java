package uk.ac.cam.dcm41.fjava.tick5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import uk.ac.cam.cl.fjava.messages.ChangeNickMessage;
import uk.ac.cam.cl.fjava.messages.ChatMessage;
import uk.ac.cam.cl.fjava.messages.Message;
import uk.ac.cam.cl.fjava.messages.RelayMessage;
import uk.ac.cam.cl.fjava.messages.StatusMessage;

public class ClientHandler {

	private static Message sentinelEmpty = new Message();
	private final Socket socket;
	private final MultiQueue<Message> multiQueue;
	private String nickname;
	private MessageQueue<Message> clientMessages;
	private final Database database;

	public ClientHandler(final Socket s, final MultiQueue<Message> q, Database d) throws SQLException {
		socket = s;
		multiQueue = q;
		database = d;
		clientMessages = new SafeMessageQueue<>();
		multiQueue.register(clientMessages);
		nickname = String.format("Anonymous%5d", new Random().nextInt(100_000));
		
		database.incrementLogins();
		{
			// Recent messages
			final List<RelayMessage> listRm = database.getRecent();
			Collections.reverse(listRm);
			for (RelayMessage rm : listRm) {
				clientMessages.put(rm);
			}
		}

		multiQueue.put(
			new StatusMessage(
				String.format("%s connected from %s.", 
								nickname, socket.getInetAddress().getCanonicalHostName())));

		final Thread fromClient = new Thread() {
			@Override
			public void run() {

				try (final ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

					while (true) {
						
						final Object inObj = input.readObject();
						if (!(inObj instanceof Message)) {
							continue;
						}
						
						final Message inMsg = (Message) inObj;
						

						if (inMsg instanceof ChangeNickMessage) {

							final String newNick = ((ChangeNickMessage) inMsg).name;
							multiQueue.put(
								new StatusMessage(
									String.format("%s is now known as %s.", nickname, newNick)));
							nickname = newNick;

						} else if (inMsg instanceof ChatMessage) {
							
							final RelayMessage rm = new RelayMessage(nickname, (ChatMessage) inMsg);
							multiQueue.put(rm);
							try {
								database.addMessage(rm);
							} catch (SQLException e) {
								e.printStackTrace();
							}

						} // end if
						
					} // end while

				} catch (final IOException | ClassNotFoundException e) {
					multiQueue.deregister(clientMessages);
					multiQueue.put(new StatusMessage(nickname + " has disconnected."));
				} finally {
					clientMessages.put(sentinelEmpty);
				} // end try-with-resources
			} // end run
		}; // end Thread
		
		fromClient.start();
		
		final Thread toClient = new Thread() {
			@Override
			public void run() {
				try (final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

					while (true) {

						final Message inMsg = clientMessages.take();
						if (inMsg != sentinelEmpty) {
							output.writeObject(inMsg);
						} else {
							break;
						}

					} // end while 
						
				} catch (final IOException e) {
						e.printStackTrace();
				} // end try-with-resources
			} // end run
		}; // end Thread

		toClient.start();
	}

}
