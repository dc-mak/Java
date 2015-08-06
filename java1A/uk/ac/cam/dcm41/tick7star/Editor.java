package uk.ac.cam.dcm41.tick7star;

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

public class Editor extends JFrame {
	private PatternPanel patternPanel;
	private GameBoardPanel gameBoardPanel;
	private FilmStripPanel filmStripPanel; 
	private World world;

	public Editor() {
		//Create JFrame and set up behaviour and layout.
		super("Editor");
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JComponent patternPanel = createPatternPanel();
		add(patternPanel, BorderLayout.WEST);
		
		JComponent gameBoardPanel = createGameBoardPanel();
		add(gameBoardPanel, BorderLayout.CENTER);

		JComponent filmStripPanel = createFilmStripPanel();
		add(filmStripPanel, BorderLayout.SOUTH);
	}

	private void addBorder(JComponent component, String title) {
		Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border tb = BorderFactory.createTitledBorder(etch,title);
		component.setBorder(tb);
	}

	private JComponent createPatternPanel() {
		patternPanel = new PatternPanel(){
			public void onPatternChange() { 
				resetWorld();
			}
		};

		addBorder(patternPanel, Strings.PANEL_PATTERN);
		return patternPanel;
	}

	private JComponent createGameBoardPanel() {
		JPanel holder = new JPanel(); 
		addBorder(holder, Strings.PANEL_GAMEVIEW);
		gameBoardPanel = new GameBoardPanel(){ //Action event
			protected void onMousePressed(int x, int y){
				world.setCell(x, y, !world.getCell(x,y));
				//World	INTERFACE doesn't have printAsRows method
				//hence the need to cast.
				patternPanel.updateRows(((WorldImpl)world).getRowData());
				resetWorld();
			}
		};
		holder.add(gameBoardPanel);
		return new JScrollPane(holder);
	}

	private JComponent createFilmStripPanel(){
		JPanel holder = new JPanel(); 
		addBorder(holder, Strings.PANEL_FUTURE_GENS);
		filmStripPanel = new FilmStripPanel();
		holder.add(filmStripPanel);
		JScrollPane sp = new JScrollPane(holder);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		return sp;
	}

	private void resetWorld() { //Given code.
		Pattern current = patternPanel.getCurrentPattern();
		try {
			world = new ArrayWorld(current.getWidth(), current.getHeight());
			current.initialise(world);
		} catch (PatternFormatException e) { /* Do nothing */ }
		gameBoardPanel.display(world);
		gameBoardPanel.repaint();
		filmStripPanel.display(world);
		filmStripPanel.repaint();
	}

	public static void main(String[] args) {
		Editor editor = new Editor();
		editor.resetWorld();
		editor.setVisible(true);
	}
}
