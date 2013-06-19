package Map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JPanel;
/**
 * This class is a panel for ShortestPath drawing
 * 
 * @author Rated-R Coder: Rashid Darwish
 * 
 */
public class ShortestPath extends JPanel {
	private Polygon shortestPath;

	/**
	 * ShortestPath Constructor
	 * @param pol
	 * @param width
	 * @param hight
	 */
	public ShortestPath(Polygon pol, int width, int hight) {
		shortestPath = pol;
		setBounds(0, 0, width, hight);
		setOpaque(false);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		int k = 0;
		if (shortestPath.xpoints.length != 0) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(3));
			for (int j = 0; j < shortestPath.xpoints.length; j++) {
				if (j == shortestPath.xpoints.length - 1) {
					k = j;
					g2d.drawLine(shortestPath.xpoints[j], shortestPath.ypoints[j],
							shortestPath.xpoints[k], shortestPath.ypoints[k]);
				} else {
					k = j + 1;
					g2d.drawLine(shortestPath.xpoints[j], shortestPath.ypoints[j],
							shortestPath.xpoints[k], shortestPath.ypoints[k]);
				}
			}
		}
	}
}
