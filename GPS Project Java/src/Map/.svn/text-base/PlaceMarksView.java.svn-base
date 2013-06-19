package Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class is a panel for Amenities drawing
 * 
 * @author Rated-R Coder: Rashid Darwish
 * @author Paulius Vysniauskas
 * 
 */

public class PlaceMarksView extends JPanel{
	private HashMap<String, PlaceMarks> placesMap;
	private HashMap<String, BufferedImage> imageMap = new HashMap<String, BufferedImage>();
	final Map map;

	/**
	 * Constructor of PlacesMarksView
	 * @param map
	 * @param hashMap
	 * @param w
	 * @param h
	 */
	public PlaceMarksView(Map map, HashMap<String, PlaceMarks> hashMap, int w, int h) {
		this.map = map;
		placesMap = hashMap;
		setBounds(0, 0, w, h);
		setOpaque(false);
		try {
			loadImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		recalculateZoom();
	}

	/**
	 * @throws IOException Loading the Images
	 */
	public void loadImages() throws IOException {
		for (PlaceMarks p : placesMap.values())
			imageMap.put(p.getName(), ImageIO.read((getClass().getResource(("/images/" + p.getName() + ".png")))));
	}

	/**
	 * Recalculating the panel while zooming
	 */
	public void recalculateZoom() {
		for (String key : placesMap.keySet()) {
			if (placesMap.get(key).getRef().size() > 1) {
				placesMap.get(key).setX((map.lonToPix(
						map.mapFileReader.getNodesMap()
						.get(placesMap.get(key).getRef().get(0))
						.getLon(), map.lonPerPixel, map.xdiff) + map
						.lonToPix(map.mapFileReader
								.getNodesMap()
								.get(placesMap.get(key).getRef().get(1))
								.getLon(), map.lonPerPixel, map.xdiff)) / 2);

				placesMap.get(key).setY((map.latToPix(
						map.mapFileReader.getNodesMap()
						.get(placesMap.get(key).getRef().get(0))
						.getLat(), map.latPerPixel, map.ydiff) + map
						.latToPix(map.mapFileReader
								.getNodesMap()
								.get(placesMap.get(key).getRef().get(1))
								.getLat(), map.latPerPixel, map.ydiff)) / 2);
			} else {
				placesMap.get(key).setX(map.lonToPix(
						map.mapFileReader.getNodesMap()
						.get(placesMap.get(key).getRef().get(0))
						.getLon(), map.lonPerPixel, map.xdiff));
				placesMap.get(key).setY(map.latToPix(
						map.mapFileReader.getNodesMap()
						.get(placesMap.get(key).getRef().get(0))
						.getLat(), map.latPerPixel, map.ydiff));
			}
		}
	}

	/**
	 * recalculate the panel while dragging
	 */
	public void recalculateDrag() {
		for (PlaceMarks p : placesMap.values()) {
			p.setX(p.getX() +(map.currX - map.initX));
			p.setY(p.getY() + (map.currY - map.initY));
		}	
	}	
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		for (PlaceMarks p : placesMap.values()) {
			g2d.drawImage(imageMap.get(p.getName()), p.getX(), p.getY(), this);
		}
	}
}
