package uk.ac.cam.dcm41.fjava.tick1star;

import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

public final class ImageUpload implements Runnable {

	private final OutputStream output;
	private final File file;
	private final ImageChatClient frame;
	
	public ImageUpload(final OutputStream output, final File file, final ImageChatClient frame) {
		this.output = output;
		this.file = file;
		this.frame = frame;
	}

	@Override
	public void run() {

		try {
			
			final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ImageIO.write(ImageIO.read(file), "jpg", byteStream);
			output.write(byteStream.toByteArray());
			
		} catch (IOException ioe) {
	
			ioe.printStackTrace();
			JOptionPane.showMessageDialog(
					frame, 
					"Something went wrong and we couldn't upload your image :-(\nTry again", "Ooops...",
					JOptionPane.ERROR_MESSAGE);
			return;

		}
	}
}
