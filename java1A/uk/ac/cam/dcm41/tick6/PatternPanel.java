package uk.ac.cam.dcm41.tick6;

import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.util.List;
import java.util.ArrayList;

public class PatternPanel extends JPanel {

	private JList guiList;

	public PatternPanel() {
		super();
		setLayout(new BorderLayout());
		guiList = new JList();
		add(new JScrollPane(guiList));
	}

	public void setPatterns(List<Pattern> list) {
		ArrayList<String> names = new ArrayList<String>();

		for (Pattern p: list) 
			names.add(p.getName()+" ("+p.getAuthor()+")");
		guiList.setListData(names.toArray());
	} 
}
