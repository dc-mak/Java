package uk.ac.cam.dcm41.fjava.tick2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;

class TestMessageReadWrite {
	
	static boolean writeMessage(String message, String filename) {
		final TestMessage t = new TestMessage();
		t.setMessage(message);
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
			out.writeObject(t);
		} catch (IOException ioe) {
			return false;
		}
		
		return true;
	}
 
	static String readMessage(String location) {
		if (location.startsWith("http://")) {
			
			try {
				// URL Connection doesn't implement AutoCloseable
				final URLConnection urlconn = (new URL(location)).openConnection();
				final ObjectInputStream in = new ObjectInputStream(urlconn.getInputStream());
				 
				final TestMessage t = (TestMessage) in.readObject();
				return t.getMessage();
				
			} catch (IOException | ClassNotFoundException ioe) {
				return null;
			}
			
		} else { // Assume it's on file system locally
			
			try (final ObjectInputStream in = new ObjectInputStream(new FileInputStream(location))) {
				
				final TestMessage t = (TestMessage) in.readObject();
				return t.getMessage(); 
				
			} catch (IOException | ClassNotFoundException e) {
				return null;
			}
			
		}
		
	}
	public static void main(String[] args) {
		if (writeMessage("This is a test.", "test.jobj")) {
			System.out.println("[SUCCESS] Writing jobj test.");
		} else {
			System.out.println("[FAILURE] Writing jobj test.");
		}
		
		String msg = readMessage("http://www.cl.cam.ac.uk/teaching/current/FJava/testmessage-dcm41.jobj");
		if (msg != null) {
			System.out.printf("[SUCCESS] Message from CL: %s%n", msg);
		} else {
			System.out.println("[FAILURE] Did not receive message CL.");
		}
		
		msg = readMessage("test.jobj"); 
		if (msg != null) {
			System.out.printf("[SUCCESS] Message from disk: %s%n", msg);
		} else {
			System.out.println("[FAILURE] Did not read message from disk.");
		}
	}
}
