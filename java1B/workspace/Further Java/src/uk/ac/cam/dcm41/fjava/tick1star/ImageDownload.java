package uk.ac.cam.dcm41.fjava.tick1star;

import java.io.InputStream;

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class ImageDownload implements Runnable {

	private final InputStream input;
	private final CustomCanvas photo;

	public ImageDownload(final InputStream input, final CustomCanvas photo) {
		this.input = input;
		this.photo = photo;
	}

	@Override
	public void run() {

		final byte[] tmp = new byte[1024];
		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		boolean passedFirstMark = false;
		int retVal = 0;
		
		try {

			while (true) {

				// blocking
				retVal = input.read(tmp, 0, tmp.length);
				if (retVal == -1) return;

				for (int i = 0; i < retVal; i++) {

					if (tmp[i] == -1) {

						passedFirstMark = true;

					} else if (passedFirstMark && tmp[i] == -39) {

						imageStream.write(tmp, 0, i + 1); // 0 to i inclusive
						photo.setImage(ImageIO.read(new ByteArrayInputStream(imageStream.toByteArray())));

						// reset
						imageStream.reset();
						if (i < retVal - 1) // i+1 to tmp.length -1 inclusive
							imageStream.write(tmp, i + 1, retVal - i - 1); // write out remaining

						// blocking
						retVal = input.read(tmp, 0, tmp.length);
						if (retVal == -1) return;

						i = -1; // so i = 0 when i++ happens
						passedFirstMark = false;

					} else {

						passedFirstMark = false;

					} // end if-else if-else

				} // end for

				imageStream.write(tmp, 0, retVal);

			} // end while

		} catch (IOException ioe) {

			ioe.printStackTrace();
			System.err.println("[ERROR] Reading image in.");
			return;

		}
	}
}
