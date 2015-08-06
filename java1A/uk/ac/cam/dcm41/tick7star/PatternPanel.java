package uk.ac.cam.dcm41.tick7star;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class PatternPanel extends JPanel {

	private Pattern currentPattern;

	private final JTextField pattern;
	private final JTextField name;
	private final JTextField author;
	private final JSpinner width;
	private final JSpinner height;
	private final JSpinner startx;
	private final JSpinner starty;
	private final JTextField rows;

	private final int defWidth = 30;

	protected abstract void onPatternChange();

	private void constrain(JComponent jc) {
		Dimension preferredSize = jc.getPreferredSize();
		//To keep max width unaffected
		Dimension maximumSize = jc.getMaximumSize();
		jc.setMaximumSize(new Dimension(maximumSize.width, preferredSize.height));
	}

	public PatternPanel() {
		super();
		try {
			currentPattern = new Pattern("::8:8:0:0:0");
		} catch (PatternFormatException pfe) {
			throw new RuntimeException(pfe);
		}
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(new JLabel(Strings.PATTERN_STRING, SwingConstants.CENTER));
		add(pattern = new JTextField("::8:8:0:0:0", defWidth));
		constrain(pattern);

		add(new JLabel(Strings.PATTERN_NAME, SwingConstants.CENTER));
		add(name = new JTextField("", defWidth));
		constrain(name);

		add(new JLabel(Strings.PATTERN_AUTHOR, SwingConstants.CENTER));
		add(author = new JTextField("", defWidth));
		constrain(author);

		add(new JLabel(Strings.PATTERN_WIDTH, SwingConstants.CENTER));
		add(width = new JSpinner(new SpinnerNumberModel(8,8,1024,1)));
		constrain(width);

		add(new JLabel(Strings.PATTERN_HEIGHT, SwingConstants.CENTER));
		add(height = new JSpinner(new SpinnerNumberModel(8,8,1024,1)));
		constrain(height);

		add(new JLabel(Strings.PATTERN_STARTX, SwingConstants.CENTER));
		add(startx = new JSpinner(new SpinnerNumberModel(0,0,1024,1)));
		constrain(startx);
		
		add(new JLabel(Strings.PATTERN_STARTY, SwingConstants.CENTER));
		add(starty = new JSpinner(new SpinnerNumberModel(0,0,1024,1)));
		constrain(starty);

		add(new JLabel(Strings.PATTERN_ROWS, SwingConstants.CENTER));
		add(rows = new JTextField("", defWidth));
		constrain(rows);

		pattern.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) { fieldSync(Fields.PATTERN); }
			public void removeUpdate(DocumentEvent e) { fieldSync(Fields.PATTERN); }
			public void insertUpdate(DocumentEvent e) { fieldSync(Fields.PATTERN); }
		});

		name.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) { fieldSync(Fields.NAME); }
			public void removeUpdate(DocumentEvent e) { fieldSync(Fields.NAME); }
			public void insertUpdate(DocumentEvent e) { fieldSync(Fields.NAME); }
		});
		
		author.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) { fieldSync(Fields.AUTHOR); }
			public void removeUpdate(DocumentEvent e) { fieldSync(Fields.AUTHOR); }
			public void insertUpdate(DocumentEvent e) { fieldSync(Fields.AUTHOR); }
		});
		
		width.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				fieldSync(Fields.WIDTH);
			}
		});

		height.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				fieldSync(Fields.HEIGHT);
			}
		});

		startx.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				fieldSync(Fields.STARTX);
			}
		});

		starty.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				fieldSync(Fields.STARTY);
			}
		});

		rows.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) { fieldSync(Fields.ROWS); }
			public void removeUpdate(DocumentEvent e) { fieldSync(Fields.ROWS); }
			public void insertUpdate(DocumentEvent e) { fieldSync(Fields.ROWS); }
		});
	}

	public Pattern getCurrentPattern() {
		return currentPattern;
	}
	
	public void updateRows(RowData newRows) {
		startx.setValue((Integer)newRows.getStartx());
		starty.setValue((Integer)newRows.getStarty());
		rows.setText(newRows.getRows());
	}

	private boolean isUpdating;
	private void fieldSync(Fields field) {
		if (isUpdating) return;	
		isUpdating = true;
		try {
			if (field == Fields.PATTERN) {
				currentPattern = new Pattern(pattern.getText());
				name.setText(currentPattern.getName());
				author.setText(currentPattern.getAuthor());
				width.setValue((Integer)currentPattern.getWidth());
				height.setValue((Integer)currentPattern.getHeight());
				startx.setValue((Integer)currentPattern.getStartCol());
				starty.setValue((Integer)currentPattern.getStartRow());
				rows.setText(currentPattern.getCells());
			} else {
				switch (field) {
				case NAME:		currentPattern.setName(name.getText());
								break;
				case AUTHOR:	currentPattern.setAuthor(author.getText());
								break;
				case WIDTH:	currentPattern.setWidth((Integer)width.getValue());
								break;
				case HEIGHT:	currentPattern.setHeight((Integer)height.getValue());
								break;
				case STARTX:	currentPattern.setStartCol((Integer)startx.getValue());
								break;
				case STARTY:	currentPattern.setStartRow((Integer)starty.getValue());
								break;
				case ROWS:		currentPattern.setCells(rows.getText()); 
								break;
				}
				pattern.setText(currentPattern.printAll());
			}
		} catch (PatternFormatException pfe) { 
			/* Change nothing */ 
		} finally { 
			isUpdating = false;
			onPatternChange();
		}
	}
}
