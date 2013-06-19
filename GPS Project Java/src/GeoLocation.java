/**
 * This class contains fields and methods for creating a Geocache object that is
 * used for LocParser.java class. It has accessory methods which are
 * self-explanatory.
 * 
 * @author Batbilig Bavuudorj
 */
public class GeoLocation {

	private String name;
	private String id;
	private String lat, lon;

	/**
	 * @param name
	 * @param id
	 * @param lat
	 * @param lon
	 */
	public GeoLocation(String name, String id, String lat, String lon) {
		this.name = name;
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Geocache ID: " + getId() + ", " + "Coordinate: " + getLat() + " " + getLon();
	}
}
