package Map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * This class is a panel for HighWays drawing
 * 
 * @author Rated-R Coder: Rashid Darwish
 * @author Paulius Vysniauskas
 * 
 */
public class Highways extends JPanel {

	private final ArrayList<Polygon> highWays;
	private final ArrayList<Polygon> routeFerry;

	/**
	 * Highways Constructor
	 * @param highWayspol
	 * @param routeFerryPol
	 * @param width
	 * @param hight
	 */
	public Highways(ArrayList<Polygon> highWayspol,
			ArrayList<Polygon> routeFerryPol, int width, int hight) {
		highWays = highWayspol;
		routeFerry = routeFerryPol;
		setBounds(0, 0, width, hight);
		setOpaque(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		int k = 0;
		g.setColor(new Color(254, 254, 254));
		for (Polygon i : highWays) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(2));
			for (int j = 0; j < i.xpoints.length; j++) {
				if (j == i.xpoints.length - 1) {
					k = j;
					g2d.drawLine(i.xpoints[j], i.ypoints[j], i.xpoints[k],
							i.ypoints[k]);
				} else {
					k = j + 1;
					g2d.drawLine(i.xpoints[j], i.ypoints[j], i.xpoints[k],
							i.ypoints[k]);
				}
			}
		}
		k = 0;
		g.setColor(new Color(100, 100, 233));
		for (Polygon i : routeFerry) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(1));
			for (int j = 0; j < i.xpoints.length; j++) {
				if (j == i.xpoints.length - 1) {
					k = j;
					g2d.drawLine(i.xpoints[j], i.ypoints[j], i.xpoints[k],
							i.ypoints[k]);
				} else {
					k = j + 1;
					g2d.drawLine(i.xpoints[j], i.ypoints[j], i.xpoints[k],
							i.ypoints[k]);
				}
			}
		}
	}
}
