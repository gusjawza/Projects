package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Coastline extends JPanel {

	/**
	 * This class is a panel for Coastline drawing
	 * 
	 * @author Rated-R Coder: Rashid Darwish
	 * @author Paulius Vysniauskas
	 */
	private final ArrayList<Polygon> costlines;

	/**
	 * Costlines Constructor
	 * 
	 * @param polygon
	 * @param width
	 * @param hight
	 */
	public Coastline(ArrayList<Polygon> polygon, int width, int hight) {
		costlines = polygon;
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
		g.setColor(new Color(153, 187, 204));
		for (Polygon i : costlines) {
			g.fillPolygon(i);
		}
	}
}
