package uk.ac.cam.dcm41.fjava.tick4star;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import uk.ac.cam.cl.fjava.messages.DynamicObjectInputStream;
import uk.ac.cam.cl.fjava.messages.Execute;
import uk.ac.cam.cl.fjava.messages.Message;
import uk.ac.cam.cl.fjava.messages.NewMessageType;
import uk.ac.cam.cl.fjava.messages.RelayMessage;
import uk.ac.cam.cl.fjava.messages.StatusMessage;

public final class HeadlessServerMessages implements Runnable {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
	private final DynamicObjectInputStream in;
	private final Appendable toApp;
	private boolean connectionOpen = true;

	public void stopMessages() {
		connectionOpen = false;
	}

	public HeadlessServerMessages(final DynamicObjectInputStream in, final Appendable toApp) {
		this.in = in;
		this.toApp = toApp;
	}

	public void run() {

		final String server = "Server";
		Message incoming;
		String date;
		String user = "user";
		String msg = "msg";
		boolean execute = false;

		try {

			// Perpetual loop
			while (connectionOpen) {
				
				execute = false;
				incoming = (Message) in.readObject();
				date = DATE_FORMAT.format(incoming.getCreationTime());

				if (incoming instanceof StatusMessage) {

					user = server;
					msg = ((StatusMessage) incoming).getMessage();

				} else if (incoming instanceof RelayMessage) {

					user = ((RelayMessage) incoming).getFrom();
					msg = ((RelayMessage) incoming).getMessage();

				} else if (incoming instanceof NewMessageType) {

					final NewMessageType tmp = (NewMessageType) incoming;
					in.addClass(tmp.getName(), tmp.getClassData());
					user = "Client";
					msg = "New class " + tmp.getName() + " loaded.";

				} else {

					if (incoming.getClass().getName().startsWith("uk.ac.cam.dcm41.fjava.tick4star")) continue;

					final Field[] fields = incoming.getClass().getDeclaredFields();
					user = "Client";
					msg = incoming.getClass().getSimpleName() + ": ";

					for (int i = 0; i < fields.length; i++) {

						msg += fields[i].getName() + "(";
						try {

							fields[i].setAccessible(true);
							msg += fields[i].get(incoming) + ")" + (i == fields.length - 1 ? "" : ", ");

						} catch (ExceptionInInitializerError | IllegalAccessException e) {

							e.printStackTrace();
							msg += "<ERROR>)" + (i == fields.length - 1 ? "" : ", ");
						}

					}

					execute = true;

				}

				toApp.append(String.format("%s [%s] %s%n", date, user, msg));

				// This can't possibly be safe
				if (execute)
					for (Method m : incoming.getClass().getMethods())
						if (m.getParameterCount() == 0)
							for (Annotation a : m.getDeclaredAnnotations())
								if (a instanceof Execute)
									try {
										m.invoke(incoming);
									} catch (IllegalAccessException | InvocationTargetException e) {
										System.err.println("[ERROR] Fault executing method of remote object.");
									}
			}

		} catch (ClassNotFoundException | IOException exc) {
			if (connectionOpen) {
				exc.printStackTrace(System.err);
				System.err.println("IO Exception: Restart application");
				return;
			}
		}
	}
}
