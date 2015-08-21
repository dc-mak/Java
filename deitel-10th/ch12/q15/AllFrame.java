// Ex 12.15: All listeners. KeyBindings next...

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public final class AllFrame extends JFrame implements ActionListener, ItemListener,
	   KeyListener, MouseListener, MouseMotionListener {
	private final JTextArea out = new JTextArea(20,40);
	private final JButton act   = new JButton("Action!");	
	private final JCheckBox chk = new JCheckBox("Item");

	public AllFrame() {
		super("All Events and Listeners");
		setLayout(new FlowLayout());
		out.setLineWrap(true);
		out.setEnabled(false);
		out.setDisabledTextColor(Color.BLACK);
		add(out);
		addKeyListener(this);
		/*
		add(act);
		act.addActionListener(this);
		add(chk);
		chk.addItemListener(this);
		*/
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) { out.setText(e.toString()); }

	@Override
	public void keyReleased(KeyEvent e) { out.setText(e.toString()); }

	@Override
	public void keyTyped(KeyEvent e) { out.setText(e.toString()); }

	@Override
	public void mouseClicked(MouseEvent e) { out.setText(e.toString()); }

	@Override
	public void mouseEntered(MouseEvent e) { out.setText(e.toString()); }

	@Override
	public void mouseExited(MouseEvent e) { out.setText(e.toString()); }

	@Override
	public void mousePressed(MouseEvent e) { out.setText(e.toString()); }

	@Override
	public void mouseReleased(MouseEvent e) { out.setText(e.toString()); }

	@Override
	public void mouseDragged(MouseEvent e) { out.setText(e.toString()); }

	@Override
	public void mouseMoved(MouseEvent e) { out.setText(e.toString()); }

	@Override
	public void actionPerformed(ActionEvent e) { out.setText(e.toString()); }

	@Override
	public void itemStateChanged(ItemEvent e) { out.setText(e.toString()); }
}
