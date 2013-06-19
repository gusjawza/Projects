import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class LocParser contains methods for manipulating LOC and XML type
 * files.
 * 
 * @author Batbilig Bavuudorj
 */
public class LocParser {

	public String FilePath;
	ArrayList<Map.GeoLocation> geoLocationList;
	Document dom;

	public LocParser() {
		geoLocationList = new ArrayList<Map.GeoLocation>();
	}

	public void run() {
		parseXml();
		parseDocument("waypoint");
	}

	/**
	 * Parses the loc file and gets the DOM object
	 */
	public void parseXml() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(FilePath);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Creates a geolocation list by getting a nodelist of elements
	 * 
	 * @param tag
	 */
	private void parseDocument(String tag) {
		Element docEle = dom.getDocumentElement();
		NodeList nl = docEle.getElementsByTagName(tag);
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				geoLocationList.add(new Map.GeoLocation(getNodeValue(el, "name"), getAttributeValue(el, "name", "id"), getAttributeValue(el, "coord",
						"lat"), getAttributeValue(el, "coord", "lon")));
			}
		}
	}

	/**
	 * Returns the current attribute's value as a string.
	 * 
	 * @param ele
	 * @param tagName
	 * @param key
	 * @return
	 */
	private String getAttributeValue(Element ele, String tagName, String key) {
		String attrVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			attrVal = el.getAttribute(key);
		}
		return attrVal;
	}

	private String getNodeValue(Element el, String name) {
		String nodeVal = null;
		NodeList valueList = el.getElementsByTagName(name);
		Element subEl = (Element) valueList.item(0);
		NodeList values = subEl.getChildNodes();
		nodeVal = values.item(0).getNodeValue();
		return nodeVal;
	}

	/**
	 * print the list content
	 */
	private void printData() {
		System.out.println("Geocaches: '" + geoLocationList.size() + "' \n");
		for (Map.GeoLocation geo : geoLocationList) {
			System.out.println(geo.toString());
		}
	}

	/**
	 * Returns an Arraylist with geolocaltion objects.
	 * 
	 * @return
	 */
	public ArrayList<Map.GeoLocation> getGeoLocationList() {
		return geoLocationList;
	}
}
