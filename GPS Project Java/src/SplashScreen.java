import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

import Map.Map;

/**
 * The SplashScreen class displays the splash screen during the software
 * startup. During the SplashScreen XML files are loaded and connection to GPS
 * device is initialized.
 * 
 * @author Ionut Trancioveanu
 * @author Zarif Jawad
 * 
 */
public class SplashScreen extends JWindow {

	private int duration;
	private Map map;
	private Control control;

	public SplashScreen(int d) {
		duration = d;
	}

	/**
	 * method to show a splash screen in the center of the screen for the amount
	 * of time given in the constructor
	 */
	public void showSplash() {

		JPanel content = (JPanel) getContentPane();
		content.setBackground(Color.WHITE);
		content.setLayout(new BorderLayout());

		// Set the window's bounds, centering the window
		int width = 370;
		int height = 200;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);

		ImageIcon label1 = new ImageIcon(getClass().getResource(
				"images/Satelite.gif"));
		JLabel labelmain1 = new JLabel(label1);
		labelmain1.setBounds(400, 300, 200, 150);

		ImageIcon label2 = new ImageIcon(getClass().getResource(
				"images/FrameworkLogo.png"));
		JLabel labelmain2 = new JLabel(label2);
		labelmain2.setBounds(10, 10, 200, 150);
		content.add(labelmain1, BorderLayout.EAST);

		content.add(labelmain2, BorderLayout.SOUTH);

		JLabel copyrt = new JLabel("Copyright  © 2011, FrameWork Studio ! ",
				SwingConstants.CENTER);
		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 14));

		content.add(copyrt, BorderLayout.SOUTH);
		Color oraRed = new Color(156, 20, 20, 255);
		content.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));

		setVisible(true);

		try {
			control = new Control();
			map = new Map();
			Thread.sleep(duration);
		} catch (Exception e) {
		}
		setVisible(false);
	}

	/**
	 * @return  map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @return control
	 */
	public Control getControl() {
		return control;
	}

	/**
	 *Dislay the splash screen and dispose it after 4000 milliseconds 
	 */
	public void showSplashAndExit() {
		showSplash();
		SplashScreen splash = new SplashScreen(4000);
		splash.showSplashAndExit();
		System.exit(0);
	}
}
