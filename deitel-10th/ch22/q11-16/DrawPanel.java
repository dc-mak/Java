// GUI Exercise for Chapter 10: Inheritance.

import java.awt.Color;
import java.awt.Paint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class DrawPanel extends JPanel {
	private int shapeCount;
	private int shapeType;
	private boolean filledShape;

	private Paint currentPaint;
	private Stroke currentStroke;
	private MyShape currentShape;
	private final MyShape[]	shapes;
	private final JLabel statusLabel;

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);

		for (int i = 0; i < shapeCount; i++)
			if (shapes[i] != null)
				shapes[i].draw(g2d);
	} 

	public void setCurrentPaint(Paint p)   { currentPaint = p;  }
	public void setCurrentStroke(Stroke s) { currentStroke = s; }
	public void setFilledShape(boolean b) { filledShape = b; }
	public void setShapeType(int s) {
		if (shapeType < 0)
			throw new IllegalArgumentException(
				"Shape type must be greater than 0.");
		shapeType = s;
	}

	public void clearLastShape() {
		if (shapeCount > 0)
			shapeCount--;
		repaint();
	}

	public void clearDrawing() {
		shapeCount = 0;
		repaint();
	}

	private final class MouseHandler extends MouseAdapter
		implements MouseMotionListener {
		@Override
		public void mousePressed(MouseEvent e) {
			switch (shapeType) {
				case  0: currentShape = new MyLine();
						 break;
				case  1: currentShape = new MyRectangle(filledShape);
						 break;
				default: currentShape = new MyOval(filledShape);
			}
			currentShape.setStroke(currentStroke);
			currentShape.setPaint(currentPaint);
			currentShape.setX1(e.getPoint().x);
			currentShape.setY1(e.getPoint().y);
			shapes[shapeCount++] = currentShape;
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			currentShape.setX2(e.getPoint().x);
			currentShape.setY2(e.getPoint().y);
			currentShape = null;
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			statusLabel.setText(
				e.getPoint().x + ", " + e.getPoint().y);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			currentShape.setX2(e.getPoint().x);
			currentShape.setY2(e.getPoint().y);
			statusLabel.setText(
				e.getPoint().x + ", " + e.getPoint().y);
			repaint();
		}
	}

	public DrawPanel(JLabel l) {
		statusLabel = l;
		shapes = new MyShape[100];
		shapeCount = 0;
		shapeType = 0;
		currentShape = null;
		currentPaint = Color.BLACK;
		currentStroke = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		setBackground(Color.WHITE);
		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
	}
}
