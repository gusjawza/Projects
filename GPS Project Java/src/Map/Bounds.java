package Map;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is Bounds
 * 
 * @author Rated-R Coder: Rashid Darwish
 * 
 */
public class Bounds {
	private String minLat;
	private String maxLat;
	private String minLon;
	private String maxLon;

	/**
	 * Bounds Constructor
	 * 
	 * @param minLat
	 * @param maxLat
	 * @param minLon
	 * @param maxLon
	 */
	Bounds(String minLat, String maxLat, String minLon, String maxLon) {
		this.minLat = minLat;
		this.maxLat = maxLat;
		this.minLon = minLon;
		this.maxLon = maxLon;
	}

	/**
	 * @return Maximum Latitude
	 */
	public Double getMaxLat() {
		return Double.valueOf(maxLat);
	}

	/**
	 * set of the Maximum Latitude
	 * @param maxLat
	 */
	public void setMaxLat(String maxLat) {
		this.maxLat = maxLat;
	}

	/**
	 * @return Maximum Longitude
	 */
	public Double getMaxLon() {
		return Double.valueOf(maxLon);
	}

	/**
	 * set the Maximum Longitude
	 * @param maxLon
	 */
	public void setMaxLon(String maxLon) {
		this.maxLon = maxLon;
	}

	/**
	 * @return Minimum Latitude
	 */
	public Double getMinLat() {
		return Double.valueOf(minLat);
	}

	/**
	 * set of the Minimum Latitude
	 * @param minLat
	 */
	public void setMinLat(String minLat) {
		this.minLat = minLat;
	}

	/**
	 * @return Minimum Longitude
	 */
	public Double getMinLon() {
		return Double.valueOf(minLon);
	}

	/**
	 * Set of the minimum Longitude
	 * @param minLon
	 */
	public void setMinLon(String minLon) {
		this.minLon = minLon;
	}
}
