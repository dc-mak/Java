package uk.ac.cam.dcm41.tick6star;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import uk.ac.cam.acr31.life.World;

public class StatisticsPanel extends JPanel {

	private JLabel Generation;
	private JLabel Population;
	private JLabel MaxPop;
	private JLabel MinPop;
	private JLabel MaxGrowthRate;
	private JLabel MaxDeathRate;

	private Graph PopGraph;
	private Graph PopChangeGraph;
	private Graph RatesGraph;

	final private Stats statsData;

	private JComponent createLabels(){
		Box result = Box.createVerticalBox();
		result.add(Generation = new JLabel(Strings.STATS_GEN));
		result.add(Population = new JLabel(Strings.STATS_POP));
		result.add(MaxPop = new JLabel(Strings.STATS_MAX_POP));
		result.add(MinPop = new JLabel(Strings.STATS_MIN_POP));
		result.add(MaxGrowthRate = new JLabel(Strings.STATS_MAX_GROWTH));
		result.add(MaxDeathRate = new JLabel(Strings.STATS_MAX_DEATH));
		return result;
	}

	private JComponent createPopPanel() {
		PopGraph = new Graph(Strings.GRAPH_POP, true, statsData.getPop());
		return PopGraph;	
	}
	
	private JComponent createPopChangePanel() {
		PopChangeGraph = new Graph(Strings.GRAPH_POP_CHANGE, false, statsData.getPopChange());
		return PopChangeGraph;
	}

	private JComponent createRatesPanel() {
		RatesGraph = new Graph(Strings.GRAPH_RATES, false, statsData.getGrowthDeath());
		return RatesGraph;
	}

	public void update(World w) {
		Generation.setText(Strings.STATS_GEN+w.getGeneration());
		Population.setText(Strings.STATS_POP+w.getPopulation());

		statsData.update(w);
		MaxPop.setText(Strings.STATS_MAX_POP+statsData.getPop().getMaxValue());
		MinPop.setText(Strings.STATS_MIN_POP+statsData.getPop().getMinValue());

		if (statsData.getPop().getDataSize() > 1) {
			MaxGrowthRate.setText(Strings.STATS_MAX_GROWTH+statsData.getGrowthDeath().getMaxValue());
			MaxDeathRate.setText(Strings.STATS_MAX_DEATH+statsData.getGrowthDeath().getMinValue());
		}
		repaint();
	}

	public StatisticsPanel(){
		super();
		statsData = new Stats();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JComponent labelsPanel = createLabels();
		add(labelsPanel);
		
		add(Box.createVerticalStrut(15));
		JComponent PopPanel = createPopPanel();
		add(PopPanel);

		add(Box.createVerticalStrut(15));
		JComponent PopChangePanel = createPopChangePanel();
		add(PopChangePanel);

		add(Box.createVerticalStrut(15));
		JComponent RatesPanel = createRatesPanel();
		add(RatesPanel);
	}
}
