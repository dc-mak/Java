package uk.ac.cam.dcm41.tick6star;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.lang.Math;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import uk.ac.cam.acr31.life.World;

public class Plot extends JPanel {

	private final int STATS_LIM;
	private final boolean filled;
	private final GraphData gData;

	protected void drawString(Graphics g, String text, int x, int y, int halign, int valign) {
		FontMetrics m = g.getFontMetrics();
		Rectangle2D r = m.getStringBounds(text, g);
		x -= r.getWidth() * halign/2;
		y += r.getHeight() * valign/2;
		g.drawString(text, x, y);
	}
	
	@Override
	protected void paintComponent (Graphics g) {
		//DRAWING AXES
		//--------------------
		FontMetrics m = g.getFontMetrics();

		final double width = getWidth();
		final double height = getHeight();

		final ArrayList<Double> plotArray = gData.getPlotData();
		final int size = plotArray.size();

		final double xMin = (double)gData.getxMinGen();
		final double xMax = xMin+(double)(STATS_LIM-1);
		final double yMin;
		final double yMax;
		if (size < 1) {
			yMin = 0;
			yMax = 0;
		} else {
			yMin = Collections.min(plotArray);
			yMax = Collections.max(plotArray);
		}

		final String xMaxString = Double.toString(xMax);
		final String xMinString = Double.toString(xMin);
		final String yMaxString = Double.toString(yMax);
		final String yMinString = Double.toString(yMin);
		
		final Rectangle2D xMaxRect = m.getStringBounds(xMaxString, g);
		final Rectangle2D xMinRect = m.getStringBounds(xMinString, g);
		final Rectangle2D yMaxRect = m.getStringBounds(yMaxString, g);
		final Rectangle2D yMinRect = m.getStringBounds(yMinString, g);

		final double xAxisIndent = Math.max(xMaxRect.getWidth(), xMinRect.getWidth());
		final double yAxisIndent = Math.max(yMaxRect.getHeight(), yMinRect.getHeight());
		final double xAxisSpacer = 5;
		final double yAxisSpacer = 5;

		//Y-axis
		g.drawLine((int)(xAxisIndent+xAxisSpacer), 0, (int)(xAxisIndent+xAxisSpacer), (int)(height-yAxisIndent));
		//X-axis
		g.drawLine((int)xAxisIndent, (int)(height-yAxisIndent-yAxisSpacer), (int)width, (int)(height-yAxisSpacer-yAxisIndent));
		//Y-axis tail
		g.drawLine((int)xAxisIndent, 0, (int)(xAxisIndent+xAxisSpacer), 0);
		//X-axis tail
		g.drawLine((int)width-1, (int)(height-yAxisIndent-yAxisSpacer), (int)width-1, (int)(height-yAxisIndent));
		
 		drawString(g, xMaxString, (int)width-1, (int)height-1, 2, 0);
 		drawString(g, xMinString, (int)(xAxisIndent+xAxisSpacer), (int)height-1, 0, 0);
		drawString(g, yMaxString, (int)xAxisIndent, 0, 2, 2);
 		drawString(g, yMinString, (int)xAxisIndent, (int)(height-yAxisIndent-yAxisSpacer), 2, 0);
		
		//DRAWING PLOT AREA
		//--------------------
		g.setColor(Color.WHITE);
		g.fillRect((int)(xAxisIndent+xAxisSpacer+1), 0, (int)(width-xAxisIndent-xAxisSpacer), (int)(height-yAxisIndent-yAxisSpacer));

		if (size < 1) return;

		//RESCALING DATA TO PIXELS		
		double[] xPlot = new double[STATS_LIM];
		double[] yPlot = convertDoubles(plotArray);
		for (int i = 0; i < size; i++) {
			xPlot[i] = (double)i*(width-xAxisIndent-xAxisSpacer)/(double)STATS_LIM+xAxisIndent+xAxisSpacer;
			if (i < size) {
				yPlot[i] = (height-yAxisIndent-yAxisSpacer)*(yMax-yPlot[i])/(yMax-yMin);
			}
		}

		//CASTING NOW TO MINIMISE ROUNDING.
		int[] xPixel = new int[STATS_LIM+2];
		int[] yPixel = new int[STATS_LIM+2];
		for (int i = 0; i < size; i++) {
			xPixel[i] = (int)xPlot[i];
			yPixel[i] = (int)yPlot[i];
		}

		//To finish of the polygon fill.
		xPixel[size] = xPixel[size-1];
		yPixel[size] = (int)(height-yAxisIndent-yAxisSpacer);
		xPixel[size+1] = (int)(xAxisIndent+xAxisSpacer);
		yPixel[size+1] = (int)(height-yAxisIndent-yAxisSpacer);

		//Select a colour and fill for the type of graph.
		if (filled == true) {
			g.setColor(Color.GREEN);
			g.fillPolygon(xPixel, yPixel, size+2);
		} else {
			g.setColor(Color.RED);
			g.drawPolyline(xPixel, yPixel, size);
		}
		
	}

	public double[] convertDoubles(ArrayList<Double> inputArr) {
		double[] result = new double[STATS_LIM];
		Iterator<Double> iterator = inputArr.iterator();
		int i = 0;
		while(iterator.hasNext()) {	
			result[i] = iterator.next().doubleValue();
			i += 1; }
		return result;
	}

	public Plot(boolean b, GraphData g){
		super();
		filled = b;
		gData = g;
		STATS_LIM = g.getStatsLimit();
	}
}
