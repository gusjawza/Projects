
/**
 * This class establishes parse the Data received from the GPS device
 * @author Rated-R Coder: Rashid Darwish
 */
public class Parser {

    private String line;
    private Data data;

    /**
     * Constructor
     * @param data
     */
    Parser(Data data) {
        this.data = data;
    }

    /**
     * Receives the data string from the GPS and send them to different methods according the string name
     * @param line
     */
    public void parseNMEA(String line) {
        String[] parts = line.split(",");
        if (parts[0].contains("GPRMC")) {
            parseGPRMC(parts);
            }else if(parts[0].contains("GPGGA")){
            	parseGPGGA(parts);
            	}else if(parts[0].contains("GPVTG")){
            		parseGPVTG(parts);
            		}
        }
    /**
     * Parses the speed data
     * @param parts
     */
    private void parseGPVTG(String[] parts) {
		data.setSpeed(parts[7] + " " + parts[8]);
		}

	/**
	 * parses latitude, longitude, sats, quality and the sea level 
	 * @param parts
	 */
	private void parseGPGGA(String[] parts) {
		data.setLatitude(parts[2]);
		data.setLongitude(parts[4]); 
		data.setNumOfSats(parts[7]);
		data.setFixQulity(parts[6]);
		data.setPrecision(parts[8]);
		data.setLevelAboveSea(parts[9] + " " + parts[10]);	
	}

	/**
	 * parses the time
	 * @param parts
	 */
	private void parseGPRMC(String[] parts){
        setTime(parts[1].substring(0, 6));
        data.setDate(parts[9]);
    }
    /**
     * sets the time
     * @param time
     */
    private void setTime(String time){
        if (!time.equalsIgnoreCase("")) {
            String curTime = "";
            String temp = time.substring(0, 2);
            curTime += temp;
            curTime += ":";
            temp = time.substring(2, 4);
            curTime += temp;
            curTime += ":";
            temp = time.substring(4, 6);
            curTime += temp;
            time = curTime;
            data.setTime(time);
        }
    }

    /**
     * @return string line
     */
    public String getLine() {
        return line;
    }

    /**
     * @param line
     */
    public void setLine(String line) {
        this.line = line;
    }
}
