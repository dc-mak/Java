// Ex 12.12/13: Basic temparature conversion app.

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public final class TempFrame extends JFrame {
	private final JTextArea input = new JTextField("Enter temparature in Farenheit: ");

	private final String FAHR = "Farenheit";
	private final String CELS = "Celsius";
	private final String KELV = "Kelvin";

	private final JRadioButton fromCels = new JRadioButton(CELS);
	private final JRadioButton fromFahr = new JRadioButton(FAHR);
	private final JRadioButton fromKelv = new JRadioButton(KELV);

	private final JRadioButton toCels = new JRadioButton(CELS);
	private final JRadioButton toFahr = new JRadioButton(FAHR, true);
	private final JRadioButton toKelv = new JRadioButton(KELV);

	private final JLabel result = new JLabel("No input", SwingConstants.LEFT);
	private final JPanel leftAlign = new JPanel(new FlowLayout(FlowLayout.LEFT));

	public TempFrame(){
		super("Temparature");
		final JPanel options = new JPanel(new GridLayout(3, 2, 3, 3));
		final Box overall = Box.createVerticalBox();
		overall.add(input);
		overall.add(options);
		leftAlign.add(result);
		overall.add(leftAlign);
		add(overall);

		final ButtonGroup from = new ButtonGroup();
		from.add(fromCels);
		from.add(fromFahr);
		from.add(fromKelv);
		final ButtonGroup to = new ButtonGroup();
		to.add(toCels);
		to.add(toFahr);
		to.add(toKelv);

		options.add(fromCels);
		options.add(toCels);
		options.add(fromFahr);
		options.add(toFahr);
		options.add(fromKelv);
		options.add(toKelv);

		fromCels.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent event) {
				updateTo(false, true, true);
			}});

		fromFahr.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent event) {
				updateTo(true, false, true);
			}});

		fromKelv.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent event) {
				updateTo(true, true, false);
			}});

		input.addActionListener(new ActionListener(){
			private final double K = 273.15; // I want my Lamdas!
			private double FahrToCels(double fahr) { return (5.0/9.0 * (fahr - 32.0)); }
			private double CelsToFahr(double cels) { return (1.8 * cels + 32.0); }

			@Override
			public void actionPerformed(ActionEvent event) {
				String answer = "Please enter a valid number.";
				try {
					double temp   = Double.parseDouble(input.getText());
					if (fromCels.isSelected()) {
						if (toFahr.isSelected())
							temp = CelsToFahr(temp);
						if (toKelv.isSelected())
							temp += K;
					} else if (fromFahr.isSelected()) {
						if (toCels.isSelected())
							temp = FahrToCels(temp);
						if (toKelv.isSelected())
							temp = FahrToCels(temp)+K;
					} else /* if (fromKelv.isSelected()) */ {
						if (toCels.isSelected())
							temp -= K;
						if (toFahr.isSelected())
							temp = CelsToFahr(temp-K);
					}
					answer = String.format("%.2f", temp);
					if (fromKelv.isSelected() && temp < 0) answer = "Really?";
				} catch (NumberFormatException nfe) { /* suppress */ }
				result.setText(answer);
			}});
	}

	private void updateTo(boolean cels, boolean fahr, boolean kelv) {
		toCels.setSelected(cels);
		toFahr.setSelected(fahr);
		toKelv.setSelected(kelv);
		toCels.setEnabled(cels);
		toFahr.setEnabled(fahr);
		toKelv.setEnabled(kelv);
	}
}
