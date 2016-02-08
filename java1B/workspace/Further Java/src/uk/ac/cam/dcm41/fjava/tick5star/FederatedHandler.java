package uk.ac.cam.dcm41.fjava.tick5star;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import uk.ac.cam.cl.fjava.messages.Message;

public class FederatedHandler {

	private MessageQueue<Message> serverMessages = new SafeMessageQueue<>();

	public FederatedHandler(final Socket socket, final CustomMessageSet messageSet) {

		messageSet.register(serverMessages);

		final Thread fromOthers = new Thread() {
			@Override
			public void run() {

				try (final ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

					while (true) {
						
						final Object inObj = input.readObject();
						if (!(inObj instanceof Message)) {
							continue;
						}
						
						final Message inMsg = (Message) inObj;
						messageSet.receive(inMsg);
						
					} // end while

				} catch (final IOException | ClassNotFoundException e) {
					messageSet.deregister(serverMessages);
				} // end try-with-resources
			} // end run
		}; // end Thread
		
		fromOthers.start();
		
		final Thread toOthers = new Thread() {
			@Override
			public void run() {
				try (final ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

					while (true) {

						final Message inMsg = serverMessages.take();
						messageSet.mark(inMsg);
						output.writeObject(inMsg);

					} // end while 
						
				} catch (final IOException e) {
						e.printStackTrace();
				} // end try-with-resources
			} // end run
		}; // end Thread

		toOthers.start();
	}

}
