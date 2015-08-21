// GUI Exercise for Chapter 10: Inheritance.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class DrawPanel extends JPanel {
	private int shapeCount;
	private int shapeType;
	private boolean filledShape;

	private Color currentColor;
	private MyShape currentShape;
	private final MyShape[]	shapes;
	private final JLabel statusLabel;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < shapeCount; i++)
			if (shapes[i] != null)
				shapes[i].draw(g);
	} 

	public ArrayList<MyShape> getShapes() {
		ArrayList<MyShape> shapes = new ArrayList<>(shapeCount);
		for (int i = 0; i < shapeCount; i++)
			shapes.add(this.shapes[i]);
		return shapes;
	}

	public void setShapes(ArrayList<MyShape> shapes) {
		shapeCount = shapes.size();
		shapes.toArray(this.shapes);
		repaint();
	}

	public void setCurrentColor(Color c) { currentColor = c; }

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
			currentShape.setColor(currentColor);
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
		currentColor = Color.BLACK;
		setBackground(Color.WHITE);
		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
	}
}
