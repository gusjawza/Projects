
import java.text.DecimalFormat;

/**
 * This Class saves all data from the GPS device
 * @author Rated-R Coder: Rashid Darwish
 *
 */
public class Data {

    private String time = "";
    private String speed = "";
    private String os = "";
    private String port = "";
    private String date = "";
    private String latitude = "";
    private String longitude = "";
    private String numOfSats = "";
    private String fixQulity = "";
    private String precision = "";
    private String levelAboveSea = "";
    DecimalFormat roundObj;

    /**
     * Default Data Constructor
     */
    Data() {
        roundObj = new DecimalFormat("####0.00000");
    }

    /**
     * sets the level above sea
     * @param levelAboveSea
     */
    public void setLevelAboveSea(String levelAboveSea) {
        if (levelAboveSea.equals(" ")) {
            this.levelAboveSea = "No Signal";
        } else {
            this.levelAboveSea = levelAboveSea;
        }
    }

    /**
     * levelAboveSea getter
     * @return levelAboveSea
     */
    public String getLevelAboveSea() {
        return levelAboveSea;
    }

    /**
     * fixQulity getter
     * @return fixQulity
     */
    public String getFixQulity() {
        return fixQulity;
    }

    /**
     * fixQulity setter
     * @param fixQulity
     */
    public void setFixQulity(String fixQulity) {
        if (fixQulity.equals("")) {
            this.fixQulity = "No Signal";
        } else {
            this.fixQulity = fixQulity;
        }
    }

    /**
     * precision getter
     * @return precision
     */
    public String getPrecision() {
        return precision;
    }

    /**
     * precision setter
     * @param precision
     */
    public void setPrecision(String precision) {
        if (precision.equals("")) {
            this.precision = "No Signal";
        } else {
            this.precision = precision;
        }
    }

    /**
     * date getter
     * @return date 
     */
    public String getDate() {
        return date;
    }

    /**
     * date setter
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * speed getter
     * @return speed
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * speed setter
     * @param speed
     */
    public void setSpeed(String speed) {
        if (speed.equals(" ")) {
            this.speed = "No Signal";
        } else {
            this.speed = speed;
        }
    }

    /**
     * time getter 
     * @return time
     */
    public String getTime() {
        return time;
    }

    /**
     * time setter 
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * os getter
     * @return os
     */
    public String getOs() {
        return os;
    }

    /**
     * os setter
     * @param os
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * port getter 
     * @return port
     */
    public String getPort() {
        return port;
    }

    /**
     * port setter
     * @param port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * latitude getter
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * latitude setter
     * @param latitude
     */
    public void setLatitude(String latitude) {
        if (latitude.equals(" ")) {
            this.latitude = "No Signal";
        } else {
            latitude = roundObj.format(Double.valueOf(latitude.substring(0, 2) + "."
                    + Double.toString((Double.valueOf(latitude.substring(2)) / 100) / 60).substring(4)));
            this.latitude = latitude.replace(",", ".");//.substring(0, 2) + "." + latitude.substring(3);
        }
    }

    /**
     * longitude getter
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * set longitude
     * @param longitude
     */
    public void setLongitude(String longitude) {
        if (longitude.equals(" ")) {
            this.longitude = "No Signal";
        } else {
            longitude = roundObj.format(Double.valueOf(longitude.substring(1, 3) + Double.toString((Double.valueOf(longitude.substring(3))) / 60).substring(1)));
            this.longitude = longitude.replace(",", ".");//substring(0, 2) + "." + longitude.substring(3);
        }
    }

    /**
     * numOfSats getter
     * @return numOfSats
     */
    public String getNumOfSats() {
        return numOfSats;
    }

    /**
     * numOfSats setter
     * @param numOfSats
     */
    public void setNumOfSats(String numOfSats) {
        if (numOfSats.equals("")) {
            this.numOfSats = "No Signal";
        } else {
            this.numOfSats = numOfSats;
        }
    }
}
