package uk.ac.cam.dcm41.tick7;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.hash.HashWorld;

public class GuiLife extends JFrame {
	private PatternPanel patternPanel;
	private ControlPanel controlPanel;
	private GamePanel gamePanel;
	
	private World world;
	private int timeDelay = 500;		// delay between updates (ms)
	private int timeStep = 0;			// progress by 2^timeStep each time
	private Timer playTimer = new Timer(timeDelay, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			doTimeStep();
		}
	});

	void doTimeStep() {
		if (world != null) {
			world = world.nextGeneration(timeStep);
			gamePanel.display(world);
		}
	}

	public GuiLife() {
		super("GuiLife");
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JComponent optionsPanel = createOptionsPanel();
		add(optionsPanel, BorderLayout.WEST);
		JComponent gamePanel = createGamePanel();
		add(gamePanel, BorderLayout.CENTER);
	}

	private JComponent createOptionsPanel() {
		Box result = Box.createVerticalBox();
		result.add(createSourcePanel());
		result.add(createPatternPanel());
		result.add(createControlPanel());
		return result;
	}

	private void addBorder(JComponent component, String title) {
		Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border tb = BorderFactory.createTitledBorder(etch,title);
		component.setBorder(tb);
	}

	private JComponent createGamePanel() {
		JPanel holder = new JPanel();
		addBorder(holder,Strings.PANEL_GAMEVIEW);
		gamePanel = new GamePanel();
		holder.add(gamePanel);
		return new JScrollPane(holder);
	}

	private JComponent createSourcePanel() {
		JPanel result = new SourcePanel(){

			protected boolean setSourceFile() { // Try getting a file locally.

				JFileChooser chooser = new JFileChooser(); // Open an file picker.
				int returnVal = chooser.showOpenDialog(this);

				if (returnVal == JFileChooser.APPROVE_OPTION) { // Valid option.
					File f = chooser.getSelectedFile(); // Select file

					try { // Attempt to load patterns.
						List<Pattern> list = PatternLoader.load(new FileReader(f));
						patternPanel.setPatterns(list);
						resetWorld();
						return true; // Success so return true!
					} catch (IOException ioe) {} // Do nothing about a failure.
				}

				return false; // And simply return false in that case.
			}

			protected boolean setSourceNone() { // No source.
				world = null; // No world 
				patternPanel.setPatterns(null); // No pattern
				resetWorld();
				return true; // Always successful
			}
			protected boolean setSourceLibrary() { // Return string of online library.
				String u = "http:// www.cl.cam.ac.uk/teaching/current/ProgJava/nextlife.txt";
				return setSourceWeb(u);
			}
			protected boolean setSourceThreeStar() { // Return string of online competition.
				String u = "http:// www.cl.cam.ac.uk/teaching/current/ProgJava/competition.txt";
				return setSourceWeb(u);
			}
			private boolean setSourceWeb(String url) { // Both online, hence common code.
				try {	// Use pattern loader to load from url
					List<Pattern> list = PatternLoader.loadFromURL(url);
					patternPanel.setPatterns(list); // Set patterns in patternPanel
					resetWorld();
					return true; // Successful so return true.
				} catch (IOException ioe) {}
				return false; // If exception, do nothing and return false.
			}
		};
		addBorder(result, Strings.PANEL_SOURCE);
		return result;
	}

	private JComponent createPatternPanel() {
		patternPanel = new PatternPanel() {
			protected void onPatternChange() {
				resetWorld();
			}
		};
		addBorder(patternPanel,Strings.PANEL_PATTERN);
		return patternPanel;
	}

	private JComponent createControlPanel() {
		// Anonymous inner class created using an abstract class so that
		// the anonymous abstract inner methods can be implemented
		// here with access/ability to call setDelay and playTimer
		// without loads of parameter passing.

		controlPanel = new ControlPanel() {
			protected void onSpeedChange(int value) {
				playTimer.setDelay(1+(100-value)*10);
			}
			protected void onStepChange(int value) {
				timeStep = value;
			}
			protected void onZoomChange(int value) {
				gamePanel.setZoom(value);
			}
		}; 
		addBorder(controlPanel,Strings.PANEL_CONTROL);
		return controlPanel;
	}

	private void resetWorld() { // Given code.
		Pattern current = patternPanel.getCurrentPattern();
		world = null;
		if (current != null) {
			try {
				world = controlPanel.initialiseWorld(current);
			} catch (PatternFormatException e) {
				JOptionPane.showMessageDialog(this,
						"Error initialising world",
						"An error occurred when initialising the world. "+e.getMessage(),
						JOptionPane.ERROR_MESSAGE);
			}
		}
		gamePanel.display(world);
		repaint();
	}

	public static void main(String[] args) {
		GuiLife gui = new GuiLife();
		gui.playTimer.start();
		gui.resetWorld();
		gui.setVisible(true);
	}
}
