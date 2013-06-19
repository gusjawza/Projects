import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.code.facebookapi.FacebookException;
import com.google.code.facebookapi.FacebookJsonRestClient;

/**
 * The class update facebook status for default NaviGPS account. Status is
 * inputed by the user in the Fb class. After updating the status a message is
 * shown to display successful update.
 * 
 * @author Zarif Jawad
 * 
 */
public class SendtoFacebook {

	/**
	 * The method takes message as a parameter updates it on facebook using
	 * FacebookJsonRestClient.
	 * 
	 * @param message
	 * @throws FacebookException
	 */
	public void send(String message) throws FacebookException {

		JFrame frame = new JFrame();
		String FB_APP_API_KEY = new String("dc2e952f027acdcba64385277ecc67fc");
		String FB_APP_SECRET = new String("dd4c4e3da0547d6eeca3281b862d87da");
		String FB_SESSION_KEY = new String("00a9d53fb6e5d26578559428.0-100002323053382");

		FacebookJsonRestClient facebook = new FacebookJsonRestClient(FB_APP_API_KEY, FB_APP_SECRET, FB_SESSION_KEY);
		FacebookJsonRestClient facebookClient = facebook;
		facebookClient.stream_publish(message, null, null, null, null);
		JOptionPane.showMessageDialog(frame, "Status updated");
	}
}
