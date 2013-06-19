import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
/**
 * This class for checking Internet connection for updating the weather
 * 
 * @author Zarif Jawad
 * 
 */
public class InternetCheck {
	/**
	 * checking the Internet
	 * 
	 * @return boolean
	 */
	public boolean isInternetReachable() {
		try {
			URL url = new URL(
					"http://weather.yahooapis.com/forecastrss?w=24606084&u=c");
			HttpURLConnection urlConnect = (HttpURLConnection) url
					.openConnection();
			Object objData = urlConnect.getContent();

		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
