package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * This class is a panel for Buildings drawing
 * 
 * @author Rated-R Coder: Rashid Darwish
 * @author Paulius Vysniauskas
 */
public class Buildings extends JPanel {

	private ArrayList<Polygon> buildings;
	private ArrayList<Polygon> constructions;

	/**
	 * Buildings Constructor
	 * 
	 * @param buildingspol
	 * @param constructionPol
	 * @param width
	 * @param hight
	 */
	Buildings(ArrayList<Polygon> buildingspol,ArrayList<Polygon> constructionPol, int width, int hight) {
		buildings = buildingspol;
		constructions = constructionPol;
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
		g.setColor(new Color(195, 185, 185));
		for (Polygon i : buildings) {
			g.fillPolygon(i);
		}
		g.setColor(new Color(182, 181, 145));
		for (Polygon i : constructions) {
			g.fillPolygon(i);
		}
	}
}
