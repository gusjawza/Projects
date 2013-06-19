package Map;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is used for user interface of RssParser.java. It creates a new panel with configurations.
 * @author Zarif Jawad
 * @author Batbilig Bavuudorj
 *
 */
public class Weather {

	/**
	 * This method create new panel and connect to the Internet to get the weather forecast
	 * @return weatherPanel
	 */
	public JPanel WeatherPanel(){
		JPanel weatherPanel = new JPanel();
        weatherPanel.setSize(820,525);
        weatherPanel.setOpaque(false);
        weatherPanel.setLayout(null);
        RssParser rp = new RssParser("http://weather.yahooapis.com/forecastrss?w=24606084&u=c");
        ImageIcon WP = new ImageIcon(getClass().getResource(("/images/weatherPanel.png")));
		JLabel wp = new JLabel(WP);
		wp.setBounds(660, 5, 200, 50);
        JLabel temp = new JLabel();
        temp.setFont(new Font("Serif", Font.BOLD, 30));
		temp.setText(rp.call());
		temp.setForeground(Color.WHITE);
		temp.setBounds(770, 5, 50, 50);
		weatherPanel.add(temp);
		weatherPanel.add(wp);
		return weatherPanel;
	}	
}
