package uk.ac.cam.dcm41.fjava.tick5star;

import java.io.IOException;
import java.net.ServerSocket;

public class FederatedListener extends Thread {

	private final ServerSocket externalSoc;
	private final CustomMessageSet messageSet;

	public FederatedListener(final ServerSocket externalSoc, final CustomMessageSet messageSet) {
		this.externalSoc = externalSoc;
		this.messageSet = messageSet;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				new FederatedHandler(externalSoc.accept(), messageSet);
			}
		} catch (final IOException ioe) {

			System.err.println("[ERROR] External server socket.");
			ioe.printStackTrace();
			
		}
	}
}
