// Ex 12.11: Recreate given GUI.

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public final class PrinterFrame extends JFrame {
	private final JLabel curPrinter = new JLabel("Printer: MyPrinter", SwingConstants.LEFT);

	private final JTextArea left;

	private final JCheckBox image;
	private final JCheckBox text;
	private final JCheckBox code;

	private final JTextArea middle;

	private final JRadioButton select;
	private final JRadioButton all;
	private final JRadioButton applet;

	private final JTextArea right;

	private final JLabel printQuality = new JLabel("Print Quality: ");
	private final String[] qualityLevel = {"High", "Medium", "Low"};
	private final JComboBox<String> quality;
	private final JCheckBox printToFile;

	private final JButton ok;
	private final JButton cancel;
	private final JButton setup;
	private final JButton help;

	public PrinterFrame() {
		super("Printer");
		setLayout(new FlowLayout());
		final Box    info	 = Box.createVerticalBox();
		final JPanel printer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		final Box	 options = Box.createHorizontalBox();
		final JPanel extras  = new JPanel();
		final JPanel buttons = new JPanel(new GridLayout(4,1,5,5));

		printer.add(curPrinter);
		info.add(printer);
		info.add(options); 
		info.add(extras); 
		add(info);
		add(buttons); 

		final int height = 4;
		final int width  = 2;
		left = new JTextArea(height,2*width);
		options.add(left);

		image = new JCheckBox("Image");
		text  = new JCheckBox("Text");
		code  = new JCheckBox("Code");
		final Box printType = Box.createVerticalBox();
		printType.add(image);
		printType.add(text);
		printType.add(code);
		options.add(printType);

		middle = new JTextArea(height,width);
		options.add(middle);

		final ButtonGroup group = new ButtonGroup();
		final Box whatToPrint = Box.createVerticalBox();
		select = new JRadioButton("Selection");
		all    = new JRadioButton ("All");
		applet = new JRadioButton("Applet");
		group.add(select);
		group.add(all);
		group.add(applet);
		whatToPrint.add(select);
		whatToPrint.add(all);
		whatToPrint.add(applet);
		options.add(whatToPrint);

		right = new JTextArea(height,2*width);
		options.add(right); 

		quality = new JComboBox<String>(qualityLevel);
		printToFile = new JCheckBox("Print to File");
		extras.add(printQuality);
		extras.add(quality);
		extras.add(printToFile);

		ok = new JButton("Ok");
		cancel = new JButton("Cancel");
		setup = new JButton("Setup...");
		help = new JButton("Help");
		buttons.add(ok);
		buttons.add(cancel);
		buttons.add(setup);
		buttons.add(help);

	}
}
