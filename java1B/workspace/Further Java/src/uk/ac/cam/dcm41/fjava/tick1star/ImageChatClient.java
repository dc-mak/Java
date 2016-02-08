package uk.ac.cam.dcm41.fjava.tick1star;

import java.awt.Button;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class ImageChatClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Socket conn;
	private final JFileChooser filePicker = new JFileChooser();
	private final FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG File", "jpg", "jpeg");

	private final CustomCanvas photo = new CustomCanvas();
	private final Button upload = new Button("Upload");

	public ImageChatClient(final String server, final int port) throws UnknownHostException, IOException {

		conn = new Socket(server, port);
		final InputStream in = conn.getInputStream();
		final OutputStream out = conn.getOutputStream();

		add(photo);
		add(upload, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);

		filePicker.setFileFilter(filter);
		filePicker.setAcceptAllFileFilterUsed(false);
		upload.addActionListener(e -> {
			final int retVal = filePicker.showOpenDialog(ImageChatClient.this);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				(new Thread(new ImageUpload(out, filePicker.getSelectedFile(), ImageChatClient.this))).start();
			}
		});

		(new Thread(new ImageDownload(in, photo))).start();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {

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

		new ImageChatClient(server, port);

	}
}
