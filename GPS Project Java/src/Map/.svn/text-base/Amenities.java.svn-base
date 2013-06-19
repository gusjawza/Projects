package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class is a panel for Amenities drawing
 * 
 * @author Rated-R Coder: Rashid Darwish
 * @author Paulius Vysniauskas
 */

public class Amenities extends JPanel {
	private ArrayList<Polygon> amunitys;
	private ArrayList<Polygon> woods;

	/**
	 * Constructor
	 * @param amunityspol
	 * @param woodPol
	 * @param width
	 * @param hight
	 */
	Amenities(ArrayList<Polygon> amunityspol, ArrayList<Polygon> woodPol,int width, int hight) {
		amunitys = amunityspol;
		woods = woodPol;
		setBounds(0, 0, width, hight);
		setOpaque(false);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(246, 238, 183));
		for (Polygon i : amunitys) {
			g.fillPolygon(i);
		}
		g.setColor(new Color(141, 196, 108));
		for (Polygon i : woods) {
			g.fillPolygon(i);
		}
	}
}
