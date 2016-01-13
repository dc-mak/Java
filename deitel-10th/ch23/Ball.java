import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import java.util.Random;

public final class Ball implements Runnable {

	private static final Random rand = new Random();
	private final JPanel drawArea;

	private final Color color =
		new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

	private final double RADIUS = 30;
	private final double SPEED = rand.nextInt(15) + 15.0;

	// For squish
	private double xRad = RADIUS;
	private double yRad = RADIUS;

	// unit gradient
	private final double dx;
	private final double dy;


	// for easy flipping
	private double xDir = 2.0 * rand.nextInt(2) - 1.0;
	private double yDir = 2.0 * rand.nextInt(2) - 1.0;

	// the centre position
	private double xPos = rand.nextInt(400);
	private double yPos = rand.nextInt(400);

	public Ball(JPanel drawArea) {
		this.drawArea = drawArea;

		final double xTmp = rand.nextDouble();
		final double yTmp = rand.nextDouble();
		dx = xTmp / Math.sqrt(xTmp*xTmp + yTmp*yTmp);
		dy = yTmp / Math.sqrt(xTmp*xTmp + yTmp*yTmp);
	}

	public void run() {

		final double HEIGHT = drawArea.getHeight();
		final double WIDTH  = drawArea.getWidth();
		final double squish = 0.8;
		
		// clear previously drawn image (prevents flicker of repaint())
		final Graphics2D g2d = (Graphics2D) drawArea.getGraphics();
		g2d.setPaint(Color.WHITE);
		g2d.fill(new Ellipse2D.Double(xPos, HEIGHT-RADIUS, 2*xRad, RADIUS));
		g2d.fill(new Ellipse2D.Double(xPos, yPos, 2*xRad, 2*yRad));

		// For convenience
		final double xCen = xPos + xRad;
		final double yCen = yPos + yRad;

		if (xCen < RADIUS) {

			if (xDir == -1.0) {
				xPos = 0.0;
				if (xRad <= squish * RADIUS) xDir = 1.0;
				xRad -= dx * SPEED;
			} else if (xRad < RADIUS) {
				xPos = 0.0;
				xRad = Math.min(xRad+dx*SPEED, RADIUS);
			} else {
				xPos += xDir * dx * SPEED;
			}

		} else if (xCen + xRad > WIDTH) {

			if (xDir == 1.0) {
				if (xRad <= squish * RADIUS) xDir = -1.0;
				xRad -= dx * SPEED;
				xPos += 2*dx * SPEED;
			} else if (xRad < RADIUS) {
				xRad = Math.min(xRad+dx*SPEED, RADIUS);
				xPos -= 2*dx * SPEED;
			} else {
				xPos += xDir * dx * SPEED;
			}

		} else {
			xRad = RADIUS;
			xPos += xDir * dx * SPEED;
		}

		// y stuff
		if (yCen < RADIUS) {

			if (yDir == -1.0) {
				yPos = 0.0;
				if (yRad <= squish * RADIUS) yDir = 1.0;
				yRad -= dy * SPEED;
			} else if (yRad < RADIUS) {
				yPos = 0.0;
				yRad = Math.min(yRad+dy*SPEED, RADIUS);
			} else {
				yPos += yDir * dy * SPEED;
			}

		} else if (yCen + yRad > HEIGHT) {

			if (yDir == 1.0) {
				if (yRad <= squish * RADIUS) yDir = -1.0;
				yRad -= dy * SPEED;
				yPos += 2*dy * SPEED;
			} else if (yRad < RADIUS) {
				yRad = Math.min(yRad+dy*SPEED, RADIUS);
				yPos -= 2*dy * SPEED;
			} else {
				yPos += yDir * dy * SPEED;
			}

		} else {
			yRad = RADIUS;
			yPos += yDir * dy * SPEED;
		}

		g2d.setPaint(Color.BLACK);
		g2d.fill(new Ellipse2D.Double(xPos, HEIGHT-RADIUS, 2*xRad, RADIUS));
		g2d.setPaint(color);
		g2d.fill(new Ellipse2D.Double(xPos, yPos, 2*xRad, 2*yRad));
	}
}
