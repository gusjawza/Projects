package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class is a panel for Geocaches drawing
 * 
 * @author Paulius Vysniauskas
 */
public class Geocaches extends JPanel {

	public ArrayList<Point> geocachesList = new ArrayList<Point>();
	public String selectedGeocacheName;
	public int selectedGeocacheInd;
	URL url = getClass().getResource("/images/TreasureIcon.png");
	Image treasure = null;

	/**
	 * Constructor
	 * 
	 * @param width
	 * @param hight
	 */
	public Geocaches(int width, int hight) {
		setBounds(0, 0, width, hight);
		setOpaque(false);
		try {
			treasure = ImageIO.read(url);
		} catch (IOException e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		
		g.setColor(Color.GREEN);
		if (!geocachesList.isEmpty())
			if (selectedGeocacheName.equalsIgnoreCase("< GeoCaches >"))
				for (Point i : geocachesList) {
					g.drawImage(treasure, i.x, i.y, this);
				}
			else
				for (int i = 0; i < geocachesList.size(); i++) {
					if (i == selectedGeocacheInd)
						g.drawImage(treasure, geocachesList.get(i).x,
								geocachesList.get(i).y, this);
				}
	}

}
