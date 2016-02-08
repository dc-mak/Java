package uk.ac.cam.dcm41.fjava.tick4star;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import uk.ac.cam.cl.fjava.messages.ChangeNickMessage;
import uk.ac.cam.cl.fjava.messages.ChatMessage;
import uk.ac.cam.cl.fjava.messages.Message;
import uk.ac.cam.cl.fjava.messages.NewMessageType;
import uk.ac.cam.cl.fjava.messages.RelayMessage;
import uk.ac.cam.cl.fjava.messages.StatusMessage;

public class ClientHandler {

	private static Message sentinelEmpty = new Message();
	private final Socket socket;
	private final MultiQueue<Message> multiQueue;
	private String nickname;
	private MessageQueue<Message> clientMessages;

	private static final Class<?>[] guiClasses = new Class<?>[] {
		HeadlessServerMessages.class,
		HeadlessChatClient.class,
		CustomAppendable.class,
		ChatPanel.class,
		GUIChatClient.class
	};

	public ClientHandler(final Socket s, final MultiQueue<Message> q) {
		socket = s;
		multiQueue = q;
		clientMessages = new SafeMessageQueue<>();
		multiQueue.register(clientMessages);
		nickname = String.format("Anonymous%5d", new Random().nextInt(100_000));

		multiQueue.put(
			new StatusMessage(
				String.format("%s connected from %s.", 
								nickname, socket.getInetAddress().getCanonicalHostName())));
		try {
			for (Class<?> c : guiClasses) {
				final String name = c.getName();
				final InputStream is = c.getClassLoader().getResourceAsStream(name.replace('.', '/')+".class");
	
	
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				final byte[] tmp = new byte[4096];
				int ret = 0;
				
				while ((ret = is.read(tmp)) > 0) {
				    baos.write(tmp, 0, ret);
				}

				clientMessages.put(new NewMessageType(name, baos.toByteArray()));
			}
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		
		clientMessages.put(new GUIChatClient(socket.getLocalAddress().getHostAddress(), socket.getLocalPort()));

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
							
							multiQueue.put(
								new RelayMessage(nickname, (ChatMessage) inMsg));

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
					// Disconnect hence suppression
				} // end try-with-resources
			} // end run
		}; // end Thread
		
		toClient.start();
	}

}
