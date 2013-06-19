package Map;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * This is the main class for drawing the map. It handles map initialization,
 * zooming, dragging and everything else related.
 * 
 * @author Rated-R Coder: Rashid Darwish
 * @author Paulius Vysniauskas
 * 
 */
public class Map extends JPanel implements MouseWheelListener,
		MouseMotionListener, MouseListener {
	private double longTolerance = 0.1;
	private double latTolerance = 0.1;
	Node currentNode;
	public JLayeredPane layeredPane;
	private Buildings buildings;
	private Coastline coast;
	private Highways highways;
	private Amenities amenities;
	private CurrentLocation currentLoc;
	private Geocaches geocaches;
	private StreetViewIcon placemarks;
	ShortestPath shortestPath;
	public PlaceMarksView placesView;
	private ArrayList<Polygon> buildingspol = new ArrayList<Polygon>();
	private ArrayList<Polygon> amunityspol = new ArrayList<Polygon>();
	private ArrayList<Polygon> highWayspol = new ArrayList<Polygon>();
	private ArrayList<Polygon> waysPointspol = new ArrayList<Polygon>();
	private ArrayList<Polygon> coastlinespol = new ArrayList<Polygon>();
	private ArrayList<Polygon> constructionPol = new ArrayList<Polygon>();
	private ArrayList<Polygon> routeFerryPol = new ArrayList<Polygon>();
	private ArrayList<Polygon> woodPol = new ArrayList<Polygon>();
	private Polygon pathPol = new Polygon();

	public ArrayList<GeoLocation> geocacheList;
	private JComboBox geocacheBox;
	public String selectedGeocacheName = "";
	public int selectedGeocacheInd;

	private int mapPanelWidth = 820;
	private int mapPanelHeight = 525;

	public int zoom = 1;
	private int maxZoom = 4;

	private double prevLon = 0;
	private double prevLat = 0;
	private double currentLon = 0;
	private double currentLat = 0;
	private double dLat;
	private double dLon;
	private int latHeight;
	private int lonWidth;
	public double latPerPixel;
	public double lonPerPixel;
	private double minlat, minlon, maxlat, maxlon;
	private double homeLat = 57.7068102, homeLon = 11.9374346;

	int initX = 0;
	int initY = 0;
	int currX = 0;
	int currY = 0;
	public int xdiff = 0;
	public int ydiff = 0;
	private double ix;
	private double iy;

	private boolean started = false;
	private boolean mapLoaded = false;

	public MapFileReader mapFileReader = new MapFileReader();

	private double[] latPerPixelarr = { 2.20818505338101E-5,
			1.5587188612101247E-5, 1.000E-5,
			0.53548449295596455259668833862078E-5 };
	private double[] lonPerPixelarr = { 4.1237142857143383E-5,
			2.91085714285718E-5, 1.8674677103716517448012331620746E-5, 1.000E-5 };
	DecimalFormat roundObj;

	/**
	 * This is the default constructor.
	 */
	public Map() {
		roundObj = new DecimalFormat("####0.00");
		setLayout(null);
		setBounds(0, 75, mapPanelWidth, mapPanelHeight);
		setVisible(false);
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, mapPanelWidth, mapPanelHeight);
		layeredPane.setLayout(null);
		layeredPane.setBorder(BorderFactory.createLineBorder(Color.black));
		this.addMouseWheelListener(this);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		add(layeredPane);
		initMap();
	}

	/**
	 * This method is called from the constructor. It controls map
	 * initialization.
	 */
	private void initMap() {

		mapFileReader.read();
		mapFileReader.createEdges();
		minlat = mapFileReader.getBounds().getMinLat();
		minlon = mapFileReader.getBounds().getMinLon();
		maxlat = mapFileReader.getBounds().getMaxLat();
		maxlon = mapFileReader.getBounds().getMaxLon();
		getScale();
		xdiff = (lonWidth - this.mapPanelWidth) / 2;
		ydiff = (latHeight - this.mapPanelHeight) / 2;
		drawInitiallMap();

		WeatherCheck check = new WeatherCheck();
		if (check.isInternetReachable() == true) {
			Weather weather = new Weather();
			layeredPane.add(weather.WeatherPanel(), new Integer(12));
		}

		buildings = new Buildings(buildingspol, constructionPol, mapPanelWidth,
				mapPanelHeight);
		layeredPane.add(buildings, new Integer(3));
		highways = new Highways(highWayspol, routeFerryPol, mapPanelWidth,
				mapPanelHeight);
		layeredPane.add(highways, new Integer(4));
		amenities = new Amenities(amunityspol, woodPol, mapPanelWidth,
				mapPanelHeight);
		layeredPane.add(amenities, new Integer(6));
		coast = new Coastline(coastlinespol, mapPanelWidth, mapPanelHeight);
		layeredPane.add(coast, new Integer(2));
		currentLoc = new CurrentLocation(this, mapPanelWidth, mapPanelHeight);
		layeredPane.add(currentLoc, new Integer(9));
		geocaches = new Geocaches(mapPanelWidth, mapPanelHeight);
		layeredPane.add(geocaches, new Integer(7));
		placemarks = new StreetViewIcon(mapPanelWidth, mapPanelHeight,
				layeredPane);
		layeredPane.add(placemarks, new Integer(8));

		placemarks.streetViewIcon.setLocation(
				lonToPix(placemarks.lon, lonPerPixel, xdiff),
				latToPix(placemarks.lat, latPerPixel, ydiff));

		initGeocacheBox();
		layeredPane.add(geocacheBox, new Integer(10));
		shortestPath = new ShortestPath(pathPol, mapPanelWidth, mapPanelHeight);
		layeredPane.add(shortestPath, new Integer(11));
		placesView = new PlaceMarksView(this, mapFileReader.getPlacesMap(),
				mapPanelWidth, mapPanelHeight);
		layeredPane.add(placesView, new Integer(10));

	}

	/**
	 * This method is used for zooming. It gets zoom center from cursor position
	 * and converts those pixels into geographical coordinates.
	 */
	private void getZoomCenter() {
		ix = -((currX * lonPerPixel) - minlon);
		iy = maxlat - (currY * latPerPixel);

		// ix = -(((currX + xdiff) * lonPerPixel) - minlon);
		// iy = maxlat - ((currY + ydiff) * latPerPixel);
	}

	/**
	 * The method, used to take the required scale value from an already defined
	 * array.
	 */
	private void getScale() {
		dLat = (maxlat - minlat);
		dLon = (maxlon - minlon);
		latPerPixel = latPerPixelarr[zoom - 1];
		lonPerPixel = lonPerPixelarr[zoom - 1];
		latHeight = (int) Math.floor(dLat / latPerPixel);
		lonWidth = (int) Math.floor(dLon / lonPerPixel);
	}

	/**
	 * This method counts xdiff and ydiff values, which are required to adjust
	 * the map position according to zoom center.
	 */
	private void adjustCenterToScale() {
		xdiff = -((int) Math.floor((ix - minlon) / lonPerPixel
				+ (mapPanelWidth / 2)));
		ydiff = (int) Math.floor((maxlat - iy) / latPerPixel
				- (mapPanelHeight / 2));
	}

	/**
	 * Method to translate longitude into pixel of x axis.
	 * 
	 * @param coord
	 *            The value to convert.
	 * @param lpp
	 *            Current scale value, longitude per pixel.
	 * @param dx
	 *            The value of xdiff, for adjusting.
	 * @return Returns longitude, converted into pixels.
	 */
	int lonToPix(double coord, double lpp, int dx) {
		return (int) (Math.floor((coord - minlon) / lpp - dx));
	}

	/**
	 * Method to translate latitude into pixel of y axis.
	 * 
	 * @param coord
	 *            The value to convert.
	 * @param lpp
	 *            Current scale value, latitude per pixel.
	 * @param dy
	 *            The value of ydiff, for adjusting.
	 * @return Returns latitude, converted into pixels.
	 */
	int latToPix(double coord, double lpp, int dy) {
		return (int) (Math.floor((maxlat - coord) / lpp - dy));
	}

	/**
	 * Method, which takes data from XML reader and assigns created polygon
	 * lists to corresponding map panels.
	 */
	private void drawInitiallMap() {

		calculateInitialMap(mapFileReader.getBuildingsMap(), buildingspol);
		calculateInitialMap(mapFileReader.getConstructionMap(), constructionPol);
		calculateInitialMap(mapFileReader.getAmenityMap(), amunityspol);
		calculateInitialMap(mapFileReader.getHighwayMap(), highWayspol);
		calculateInitialMap(mapFileReader.getWaysPointsMap(), waysPointspol);
		calculateInitialMap(mapFileReader.getCoastlineMap(), coastlinespol);
		calculateInitialMap(mapFileReader.getRouteFerryMap(), routeFerryPol);
		calculateInitialMap(mapFileReader.getWoodMap(), woodPol);

	}

	/**
	 * Method, which converts hashMap of geo coordinates into list of polygons
	 * for all required map panels.
	 * 
	 * @param hashMap
	 *            HashMap of geo coordinates to translate.
	 * @param arrList
	 *            List to fill with pixels, counted from geo coordinates.
	 */
	private void calculateInitialMap(HashMap<String, WayPoints> hashMap,
			ArrayList<Polygon> arrList) {

		for (String way : hashMap.keySet()) {
			int[] xAxis = new int[hashMap.get(way).getRef().size()];
			int[] yAxis = new int[hashMap.get(way).getRef().size()];
			int i = 0;
			for (String ref : hashMap.get(way).getRef()) {
				xAxis[i] = lonToPix(mapFileReader.getNodesMap().get(ref)
						.getLon(), lonPerPixel, xdiff);
				yAxis[i] = latToPix(mapFileReader.getNodesMap().get(ref)
						.getLat(), latPerPixel, ydiff);
				i++;
			}
			arrList.add(new Polygon(xAxis, yAxis, hashMap.get(way).getRef()
					.size() - 1));
		}

	}

	/**
	 * Central method to control zooming.
	 * 
	 * @param zoom
	 *            Index of current zoom level.
	 */
	public void zoomInOrOut(int zoom) {

		getZoomCenter();
		getScale();
		adjustCenterToScale();
		zoomingObjects();
	}

	/**
	 * Method, which handles the recounting of pixels for all panels.
	 */
	private void zoomingObjects() {

		recalculateZoom(mapFileReader.getBuildingsMap(), buildingspol);
		recalculateZoom(mapFileReader.getAmenityMap(), amunityspol);
		recalculateZoom(mapFileReader.getCoastlineMap(), coastlinespol);
		recalculateZoom(mapFileReader.getHighwayMap(), highWayspol);
		recalculateZoom(mapFileReader.getWaysPointsMap(), waysPointspol);
		recalculateZoom(mapFileReader.getConstructionMap(), constructionPol);
		recalculateZoom(mapFileReader.getRouteFerryMap(), routeFerryPol);
		recalculateZoom(mapFileReader.getWoodMap(), woodPol);
		recalculateZoomPath();
		recalculateZoom();
		placesView.recalculateZoom();
	}

	/**
	 * This method recalculates pixels path from start until destination when
	 * zooming occurs. Used if actual path had been drawn.
	 */
	private void recalculateZoomPath() {
		if (mapFileReader.getPath() != null) {
			int i = 0;
			pathPol.xpoints = new int[mapFileReader.getPath().size()];
			pathPol.ypoints = new int[mapFileReader.getPath().size()];
			for (Node node : mapFileReader.getPath()) {
				pathPol.xpoints[i] = lonToPix(node.getLon(), lonPerPixel, xdiff);
				pathPol.ypoints[i] = latToPix(node.getLat(), latPerPixel, ydiff);
				i++;
			}
		} else
			shortestPath.setVisible(false);
	}

	/**
	 * The method, which takes parameters and recalculates pixels for map
	 * panels, such as buildings and roads.
	 * 
	 * @param hashMap
	 *            HashMap of geo coordinates.
	 * @param arrList
	 *            List of polygons.
	 */
	private void recalculateZoom(HashMap<String, WayPoints> hashMap,
			ArrayList<Polygon> arrList) {

		int i, k = 0;
		Double[] xAxis = {};
		Double[] yAxis = {};
		for (String way : hashMap.keySet()) {
			xAxis = new Double[hashMap.get(way).getRef().size()];
			yAxis = new Double[hashMap.get(way).getRef().size()];
			i = 0;
			for (String ref : hashMap.get(way).getRef()) {
				xAxis[i] = mapFileReader.getNodesMap().get(ref).getLon();
				yAxis[i] = mapFileReader.getNodesMap().get(ref).getLat();
				i++;
			}
			for (int j = 0; j < arrList.get(k).xpoints.length; j++) {
				arrList.get(k).xpoints[j] = lonToPix(xAxis[j], lonPerPixel,
						xdiff);
				arrList.get(k).ypoints[j] = latToPix(yAxis[j], latPerPixel,
						ydiff);
			}
			k++;
		}
	}

	/**
	 * Similar method for recalculating pixels when zooming, used to process the
	 * repaint of current location, the flags(if activated) and shown geocaches.
	 */
	private void recalculateZoom() {
		currentLoc.currPosX = lonToPix(currentLon, lonPerPixel, xdiff);
		currentLoc.currPosY = latToPix(currentLat, latPerPixel, ydiff);
		currentLoc.greenFlagX = lonToPix(currentLoc.greenFlagLon, lonPerPixel,
				xdiff);
		currentLoc.greenFlagY = latToPix(currentLoc.greenFlagLat, latPerPixel,
				ydiff);
		currentLoc.redFlagX = lonToPix(currentLoc.redFlagLon, lonPerPixel,
				xdiff);
		currentLoc.redFlagY = latToPix(currentLoc.redFlagLat, latPerPixel,
				ydiff);
		if (!geocaches.geocachesList.isEmpty()) {
			geocaches.geocachesList = new ArrayList<Point>();
			for (GeoLocation geo : geocacheList) {
				geocaches.geocachesList.add(new Point(lonToPix(
						Double.valueOf(geo.getLon()), lonPerPixel, xdiff),
						latToPix(Double.valueOf(geo.getLat()), latPerPixel,
								ydiff)));
			}
		}
		placemarks.streetViewIcon.setLocation(
				lonToPix(placemarks.lon, lonPerPixel, xdiff),
				latToPix(placemarks.lat, latPerPixel, ydiff));
	}

	/**
	 * Central method to handle map dragging.
	 */
	private void draggingMap() {
		recalculateDrag(buildingspol);
		recalculateDrag(coastlinespol);
		recalculateDrag(highWayspol);
		recalculateDrag(amunityspol);
		recalculateDrag(waysPointspol);
		recalculateDrag(constructionPol);
		recalculateDrag(routeFerryPol);
		recalculateDrag(woodPol);
		recalculateDragPath(pathPol);
		recalculateDrag();
		placesView.recalculateDrag();
	}

	/**
	 * Universal method to convert existing list of pixels when dragging occurs.
	 * 
	 * @param pol
	 *            List of polygons to convert.
	 */
	private void recalculateDrag(ArrayList<Polygon> pol) {
		for (Polygon p : pol) {
			for (int i = 0; i < p.xpoints.length; i++) {
				p.xpoints[i] += (currX - initX);
				p.ypoints[i] += (currY - initY);
			}
		}
	}

	/**
	 * This method recalculates pixels for path from start to destination if it
	 * is drawn.
	 * 
	 * @param pol
	 *            List of polygons.
	 */
	private void recalculateDragPath(Polygon pol) {
		if (mapFileReader.getPath() != null) {
			for (int i = 0; i < pol.xpoints.length; i++) {
				pol.xpoints[i] += (currX - initX);
				pol.ypoints[i] += (currY - initY);
				repaint();
			}
		} else
			shortestPath.setVisible(false);
	}

	/**
	 * Method, which recalculates the pixels for current position pointer, the
	 * flags(if activated) and placemarks.
	 */
	private void recalculateDrag() {

		currentLoc.currPosX += (currX - initX);
		currentLoc.currPosY += (currY - initY);
		currentLoc.greenFlagX += (currX - initX);
		currentLoc.greenFlagY += (currY - initY);
		currentLoc.redFlagX += (currX - initX);
		currentLoc.redFlagY += (currY - initY);

		for (Point point : geocaches.geocachesList) {
			point.x += (currX - initX);
			point.y += (currY - initY);
		}
		placemarks.streetViewIcon.setLocation(placemarks.streetViewIcon.getX()
				+ (currX - initX), placemarks.streetViewIcon.getY()
				+ (currY - initY));

	}

	/**
	 * This method sets values of current position and checks for closest node.
	 * Has two parts, one of them is used on initialization, another anytime
	 * else.
	 * 
	 * @param gpsLon
	 *            Value of current longitude, comes from the Gps receiver.
	 * @param gpsLat
	 *            Value of current latitude, comes from the Gps receiver.
	 */
	public void setCurrentLoc(String gpsLon, String gpsLat) {
		findNearestCurrentNode(Float.valueOf(gpsLat), Float.valueOf(gpsLon));
		if (!mapLoaded) {
			prevLat = Double.valueOf(gpsLat);
			prevLon = Double.valueOf(gpsLon);
			dLat = Double.valueOf(gpsLat) - prevLat;
			dLon = Double.valueOf(gpsLon) - prevLon;
			currentLat = (Double.valueOf(gpsLat) - dLat);
			currentLon = (Double.valueOf(gpsLon) - dLon);
			currentLoc.currPosX += Math.abs(lonToPix(currentLon, lonPerPixel,
					xdiff) - lonToPix(prevLon, lonPerPixel, xdiff));
			currentLoc.currPosY += Math.abs(latToPix(currentLat, latPerPixel,
					ydiff) - latToPix(prevLat, latPerPixel, ydiff));
			prevLat = currentLat;
			prevLon = currentLon;
			recalculateZoom();
			mapLoaded = true;
		}
		dLat = Double.valueOf(gpsLat) - prevLat;
		dLon = Double.valueOf(gpsLon) - prevLon;
		currentLat = (Double.valueOf(gpsLat));
		currentLon = (Double.valueOf(gpsLon));
		currentLoc.currPosX += (lonToPix(currentLon, lonPerPixel, xdiff) - lonToPix(
				prevLon, lonPerPixel, xdiff));
		currentLoc.currPosY += (latToPix(currentLat, latPerPixel, ydiff) - latToPix(
				prevLat, latPerPixel, ydiff));
		prevLat = currentLat;
		prevLon = currentLon;
		currentLoc.repaint();
	}

	/**
	 * This method goes through the list of nodes and finds the closest to the
	 * current position.
	 * 
	 * @param lat
	 *            Value of current latitude.
	 * @param lon
	 *            Value of current longitude.
	 */
	public void findNearestCurrentNode(double lat, double lon) {
		ArrayList<Double> nearestNode = new ArrayList<Double>();
		HashMap<Double, Node> currentLocMap = new HashMap<Double, Node>();
		for (WayPoints ways : mapFileReader.getHighwayMap().values()) {
			for (String ref : ways.getRef()) {
				if (((lat - latTolerance) <= Double.valueOf(mapFileReader
						.getNodesMap().get(ref).getLat().toString()))
						&& ((lat + latTolerance) >= Double
								.valueOf(mapFileReader.getNodesMap().get(ref)
										.getLat().toString()))
						&& (((lon - longTolerance) <= Double
								.valueOf(mapFileReader.getNodesMap().get(ref)
										.getLon().toString())) && ((lon + longTolerance) >= Double
								.valueOf(mapFileReader.getNodesMap().get(ref)
										.getLon().toString())))) {
					currentLocMap.put(
							distFrom(lat, lon,
									mapFileReader.getNodesMap().get(ref)),
							mapFileReader.getNodesMap().get(ref));
					nearestNode.add(distFrom(lat, lon, mapFileReader
							.getNodesMap().get(ref)));
				}
			}

			currentNode = currentLocMap.get(Collections.min(nearestNode));
		}
	}

	/**
	 * This method goes through the list of node and finds the closest to the
	 * destination place.
	 * 
	 * @param lat
	 *            Value of destination place latitude.
	 * @param lon
	 *            Value of destination places longitude.
	 * @return
	 */
	public Node findGeoNode(double lat, double lon) {
		HashMap<Double, Node> GeoNodes = new HashMap<Double, Node>();
		ArrayList<Double> dist = new ArrayList<Double>();
		for (WayPoints ways : mapFileReader.getHighwayMap().values()) {
			for (String ref : ways.getRef()) {
				if (((lat - latTolerance) <= mapFileReader.getNodesMap()
						.get(ref).getLat())
						&& ((lat + latTolerance) >= mapFileReader.getNodesMap()
								.get(ref).getLat())
						&& (((lon - longTolerance) <= mapFileReader
								.getNodesMap().get(ref).getLon()) && ((lon + longTolerance) >= mapFileReader
								.getNodesMap().get(ref).getLon()))) {
					GeoNodes.put(
							distFrom(lat, lon,
									mapFileReader.getNodesMap().get(ref)),
							mapFileReader.getNodesMap().get(ref));
					dist.add(distFrom(lat, lon, mapFileReader.getNodesMap()
							.get(ref)));
				}
			}
		}
		return GeoNodes.get(Collections.min(dist));
	}

	/**
	 * Method to calculate shortest path from current position until the
	 * selected geocache.
	 * 
	 * @param lat
	 *            Latitude of the selected geocache.
	 * @param lon
	 *            Longitude of the selected geocache.
	 */
	private void shortestPath(double lat, double lon) {
		shortestPath.setVisible(false);
		mapFileReader.locatePathes(currentNode, findGeoNode(lat, lon));
		if (mapFileReader.getPath() != null) {
			zoomInOrOut(zoom);
			shortestPath.setVisible(true);
		}
	}

	/**
	 * Method to calculate shortest path from from starting flag until ending
	 * flag. Only when both flags are activated.
	 * 
	 * @param lat
	 * @param lon
	 * @param lat2
	 * @param lon2
	 */
	void flagsShortestPath(double lat, double lon, double lat2, double lon2) {
		shortestPath.setVisible(false);
		mapFileReader.locatePathes(findGeoNode(lat, lon),
				findGeoNode(lat2, lon2));
		if (mapFileReader.getPath() != null) {
			zoomInOrOut(zoom);
			shortestPath.setVisible(true);
		}
	}

	/**
	 * This method calculates shortest path from current position until home(in
	 * this case it is Patricia building). Only when Return button clicked.
	 */
	public void homeShortestPath() {
		shortestPath.setVisible(false);
		mapFileReader.locatePathes(currentNode, findGeoNode(homeLat, homeLon));
		if (mapFileReader.getPath() != null) {
			zoomInOrOut(zoom);
			shortestPath.setVisible(true);
		}
	}

	/**
	 * This method calculates the distance between two nodes.
	 * 
	 * @param lat
	 *            The latitude of first node.
	 * @param lon
	 *            The longitude of second node.
	 * @param secondNode
	 *            Object of the second node.
	 * @return The value of distance.
	 */
	public double distFrom(double lat, double lon, Node secondNode) {
		double dLat = Math.toRadians(secondNode.getLat() - lat);
		double dLng = Math.toRadians(secondNode.getLon() - lon);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat))
				* Math.cos(Math.toRadians(secondNode.getLat()))
				* Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return Double.parseDouble(roundObj.format(3958.75 * c * 1609).replace(
				",", "."));
	}

	/**
	 * This method initializes the drop-down menu for geocache list and handles
	 * events if geocache is selected(counts the shortest path until chosen
	 * geocache).
	 */
	public void initGeocacheBox() {
		geocacheBox = new JComboBox();
		geocacheBox.setBounds(700, 60, 115, 20);
		selectedGeocacheName = "< GeoCaches >";
		geocaches.selectedGeocacheName = "< GeoCaches >";
		geocacheBox.addItem(selectedGeocacheName);
		geocacheBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String item = (String) e.getItem();
				if (!item.equalsIgnoreCase(selectedGeocacheName)) {
					selectedGeocacheName = item;
					geocaches.selectedGeocacheName = selectedGeocacheName;
					for (int i = 0; i < geocacheList.size(); i++)
						if (geocacheList.get(i).getName()
								.equalsIgnoreCase(selectedGeocacheName)) {
							geocaches.selectedGeocacheInd = i;
							shortestPath(Double.valueOf(geocacheList.get(i)
									.getLat()), Double.valueOf(geocacheList
									.get(i).getLon()));
							geocaches.selectedGeocacheName = selectedGeocacheName;
						}
				}
			}
		});
	}

	/**
	 * This method fill the geocache drop-down menu if the loc file had been
	 * loaded.
	 */
	public void fillDropDownMenu() {
		if (!geocacheList.isEmpty()) {
			for (GeoLocation geo : geocacheList) {
				geocacheBox.addItem(geo.getName());
			}
			geocaches.geocachesList = new ArrayList<Point>();
			for (GeoLocation geo : geocacheList) {
				geocaches.geocachesList.add(new Point(lonToPix(
						Double.valueOf(geo.getLon()), lonPerPixel, xdiff),
						latToPix(Double.valueOf(geo.getLat()), latPerPixel,
								ydiff)));
			}
			xdiff = (lonWidth - this.mapPanelWidth) / 2;
			ydiff = (latHeight - this.mapPanelHeight) / 2;
			zoomInOrOut(zoom);
		}
	}

	/**
	 * Event listener for mouse wheel, needed for zooming.
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {

		int command = arg0.getWheelRotation();
		if (command < 0) {
			if (zoom < maxZoom - 1) {
				zoom++;
				currX = arg0.getX();
				currY = arg0.getY();
				zoomInOrOut(zoom);
			}
		} else if (command > 0) {
			if (zoom > 1) {
				zoom--;
				currX = arg0.getX();
				currY = arg0.getY();
				zoomInOrOut(zoom);
			}
		}
		updateThePainting();
	}

	/**
	 * Event listener for mouse dragging, needed when map is being dragged.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (!started) {
			initX = e.getX();
			initY = e.getY();
			started = !started;
		}
		currX = e.getX();
		currY = e.getY();
		draggingMap();
		initX = currX;
		initY = currY;
		updateThePainting();
	}

	/**
	 * Event listener method, gets current mouse cursor position.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		initX = e.getX();
		initY = e.getY();

	}

	/**
	 * Event listener method, shows pop-up menu if right mouse button clicked.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		started = !started;
		if (arg0.isMetaDown()) {
			currentLoc.showPopup(arg0.getComponent(), arg0.getX(), arg0.getY(),
					xdiff, ydiff, lonPerPixel, latPerPixel, minlon, maxlat);
		}
	}

	/**
	 * Empty mouse listener method.s
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
	}

	/**
	 * Empty mouse listener method.s
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	/**
	 * Empty mouse listener method.s
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	/**
	 * Event listener method, shows pop-up menu if right mouse button clicked.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.isMetaDown()) {
			currentLoc.showPopup(arg0.getComponent(), arg0.getX(), arg0.getY(),
					xdiff, ydiff, lonPerPixel, latPerPixel, minlon, maxlat);
		}
	}

	/**
	 * Method that calls repaint() for several panels at once.
	 */
	private void updateThePainting() {
		coast.repaint();
		buildings.repaint();
		highways.repaint();
		amenities.repaint();
		currentLoc.repaint();
		shortestPath.repaint();
	}
}
