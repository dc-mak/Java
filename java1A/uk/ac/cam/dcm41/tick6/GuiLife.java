package uk.ac.cam.dcm41.tick6;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import uk.ac.cam.acr31.life.World;

public class GuiLife extends JFrame {
	private PatternPanel patternPanel;
	private ControlPanel controlPanel;
	private GamePanel gamePanel;

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
		JPanel result = new SourcePanel();
		addBorder(result,Strings.PANEL_SOURCE);
		return result;
	}

	private JComponent createPatternPanel() {
		patternPanel = new PatternPanel();
		addBorder(patternPanel,Strings.PANEL_PATTERN);
		return patternPanel;
	}

	private JComponent createControlPanel() {
		controlPanel = new ControlPanel();
		addBorder(controlPanel,Strings.PANEL_CONTROL);
		return controlPanel;
	}

	public static void main(String[] args) {
		GuiLife gui = new GuiLife();
		try {
			List<Pattern> list;
			CommandLineOptions c = new CommandLineOptions(args);

			if (c.getSource().startsWith("http://")) {
				list = PatternLoader.loadFromURL(c.getSource());
			} else {
				list = PatternLoader.loadFromDisk(c.getSource());
			}

			gui.patternPanel.setPatterns(list);

			Pattern p = list.get(0);
			if (c.getIndex() != null)
				p = list.get(c.getIndex());

			World w = gui.controlPanel.initialiseWorld(p);
			gui.gamePanel.display(w);
		} catch (CommandLineException cfe) {
			System.out.println(cfe.getMessage());
			return;
		} catch (IOException ioe) {
			System.out.println("Error: file handling malfunction");
			return;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Error: Index out of bounds");
			return;
		} catch (PatternFormatException pfe) {
			System.out.print(pfe.getMessage());
			return;
		}
		gui.setVisible(true);
	}
}
