import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The NaviScreen displays the main NaviPanel, which shows us the map and other
 * features which are shown on main navigation screen.
 * 
 * @author Paulius Vysniauskas
 * @author Zarif Jawad
 * @author David Giorgidze
 * @author Ionut Trancioveanu
 * @author Rated-R Coder: Rashid Darwish
 * @author Batbilig Bavuudorj
 * 
 */
public class NaviScreen extends MainMenu {

	public JLabel Signals1;
	public JLabel Signals2;
	public JLabel Signals3;
	public JLabel Signals4;
	public JLabel Signals5;
	public JLabel levelAboveSea;
	public int Satelliteno;
	public static JLabel NaviPanelN;
	public static JLabel NaviPanelD;
	private MainMenu mainObj;

	/**
	 * Empty Constructor
	 */
	public NaviScreen() {

	}

	/**
	 * NaviscreenDisplay returns a NaviPanel, which contains the map and all the
	 * other features. Method updates data from the GPS device in every 1000
	 * milliseconds. Method creates objects of Fb, twitter and Notes class to
	 * display the social features. Method contains the ActionListeners for
	 * Position,Return and PlaceMarks buttons.
	 * 
	 * @return NaviPanel
	 */
	public JPanel NaviscreenDisplay() {

		// Creating Level Above sea
		final JLabel levelAboveSea = new JLabel();
		levelAboveSea.setVisible(true);
		levelAboveSea.setBounds(10, 600, 300, 50);
		levelAboveSea.setFont(new Font("Serif", Font.BOLD, 26));
		levelAboveSea.setForeground(Color.WHITE);

		// Creating Distance Label
		final JLabel distanceGeo = new JLabel();
		distanceGeo.setVisible(true);
		distanceGeo.setBounds(300, 600, 500, 50);
		distanceGeo.setFont(new Font("Serif", Font.BOLD, 26));
		distanceGeo.setForeground(Color.WHITE);

		// Creating Navi Menu
		final JPanel NaviPanel = new JPanel();
		NaviPanel.setSize(1024, 640);
		NaviPanel.setLayout(null);
		NaviPanel.setVisible(true);

		// Creating Navi Journal
		ImageIcon NaviJournalB = new ImageIcon(getClass().getResource("images/NaviJournal.png"));
		final JLabel journalB = new JLabel(NaviJournalB);
		journalB.setBounds(805, 335, 260, 110);

		journalB.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				Notes note = new Notes();
				note.start();
			}
		});

		// Creating map on NaviScreen
		ImageIcon NaviPaneliconN = new ImageIcon(getClass().getResource("images/NaviMapBackgroundNightMode.png"));
		NaviPanelN = new JLabel(NaviPaneliconN);
		NaviPanelN.setVisible(true);
		NaviPanelN.setBounds(0, 0, 1024, 640);

		ImageIcon NaviPaneliconD = new ImageIcon(getClass().getResource("images/NaviMapBackgroundDaylightMode.png"));
		NaviPanelD = new JLabel(NaviPaneliconD);
		NaviPanelD.setVisible(false);
		NaviPanelD.setBounds(0, 0, 1024, 640);

		// Creating the button labels for the panel
		ImageIcon NaviPositionB = new ImageIcon(getClass().getResource("images/PositionButton.png"));
		final JLabel positionB = new JLabel(NaviPositionB);
		positionB.setBounds(805, 50, 260, 110);

		positionB.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Position position = new Position();
				position.Position(mainObj.data, mainObj);

			}
		});

		ImageIcon NaviReturnB = new ImageIcon(getClass().getResource("images/ReturnButton.png"));
		final JLabel naviReturnB = new JLabel(NaviReturnB);
		naviReturnB.setBounds(805, 140, 260, 110);
		naviReturnB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainObj.map.homeShortestPath();
			}
		});

		ImageIcon NaviPlaceMarkB = new ImageIcon(getClass().getResource("images/PlaceMarkButton.png"));
		final JLabel placeMarkB = new JLabel(NaviPlaceMarkB);
		placeMarkB.setBounds(805, 240, 260, 110);
		placeMarkB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (mainObj.map.placesView.isVisible())
					mainObj.map.placesView.setVisible(false);
				else
					mainObj.map.placesView.setVisible(true);
			}

		});

		// Creating Proximity Buttons
		ImageIcon ProximityON = new ImageIcon(getClass().getResource("images/ProximityON.png"));
		final JLabel proximityON = new JLabel(ProximityON);
		proximityON.setBounds(880, 430, 50, 50);
		proximityON.setVisible(true);

		ImageIcon ProximityOFF = new ImageIcon(getClass().getResource("images/ProximityOFF.png"));
		final JLabel proximityOFF = new JLabel(ProximityOFF);
		proximityOFF.setBounds(880, 430, 50, 50);
		proximityOFF.setVisible(false);
		proximityON.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				proximityON.setVisible(false);
				proximityOFF.setVisible(true);
			}

		});

		proximityOFF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				proximityON.setVisible(true);
				proximityOFF.setVisible(false);
			}

		});

		// Adding Volume ON && OFF buttons
		ImageIcon VolumeON = new ImageIcon(getClass().getResource("images/VolumeON.png"));
		final JLabel volumeON = new JLabel(VolumeON);
		volumeON.setBounds(950, 430, 50, 50);
		volumeON.setVisible(true);

		ImageIcon VolumeOFF = new ImageIcon(getClass().getResource("images/VolumeOFF.png"));
		final JLabel volumeOF = new JLabel(VolumeOFF);
		volumeOF.setBounds(950, 430, 50, 50);
		volumeOF.setVisible(false);

		volumeON.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				volumeON.setVisible(false);
				volumeOF.setVisible(true);
			}
		});
		volumeOF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				volumeON.setVisible(true);
				volumeOF.setVisible(false);
			}
		});

		ImageIcon NaviBackB = new ImageIcon(getClass().getResource("images/BackButton.png"));
		final JLabel backB = new JLabel(NaviBackB);
		backB.setBounds(860, 540, 160, 80);
		ImageIcon NaviBackBG = new ImageIcon(getClass().getResource("images/BackButtonGlow.png"));
		final JLabel backBG = new JLabel(NaviBackBG);
		backBG.setBounds(860, 540, 160, 80);
		backBG.setVisible(false);
		backB.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent evt) {
				backBG.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				backBG.setVisible(true);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				NaviPanel.setVisible(false);
				mainObj.mainMenuPanel.setVisible(true);
				mainObj.map.setVisible(false);

			}
		});

		final JLabel Signals5 = new JLabel();
		Signals5.setBounds(665, -17, 80, 80);
		final JLabel Time = new JLabel();
		Time.setBounds(780, -3, 200, 50);
		Time.setFont(new Font("Serif", Font.BOLD, 26));
		Time.setForeground(Color.WHITE);
		NaviPanel.add(Signals5);
		final JLabel Lon = new JLabel();
		Lon.setBounds(520, 0, 100, 50);
		Lon.setFont(new Font("Serif", Font.BOLD, 16));
		Lon.setForeground(Color.WHITE);
		final JLabel Lat = new JLabel();
		Lat.setBounds(410, 0, 100, 50);
		Lat.setFont(new Font("Serif", Font.BOLD, 16));
		Lat.setForeground(Color.WHITE);

		// Creating Facebook and twitter buttons
		ImageIcon FB = new ImageIcon(getClass().getResource("images/fb.png"));
		final JLabel fb = new JLabel(FB);
		fb.setBounds(880, 490, 50, 50);
		fb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				Fb frs = new Fb();
				frs.show();
			}
		});

		ImageIcon TW = new ImageIcon(getClass().getResource("images/tw.png"));
		final JLabel tw = new JLabel(TW);
		tw.setBounds(950, 490, 48, 48);

		tw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				twitter tweet = new twitter();
				tweet.popup();
			}
		});

		// Changing the image icon for the signal
		ActionListener actionListener1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				String a = mainObj.data.getNumOfSats();

				levelAboveSea.setText("Level Above Sea " + mainObj.data.getLevelAboveSea());

				Time.setText(mainObj.data.getTime() + "GMT");
				Lon.setText("Lon: " + mainObj.data.getLongitude());
				Lat.setText("Lat: " + mainObj.data.getLatitude());
				if (mainObj.SettingsScreen.unitSystem.equalsIgnoreCase("Metric"))
					distanceGeo.setText("Distance To GeoCache : " + String.valueOf(mainObj.map.mapFileReader.getDistance()) + " m");
				else
					distanceGeo.setText("Distance To GeoCache : " + String.valueOf(Math.floor(mainObj.map.mapFileReader.getDistance() * 3.28083))
							+ " f");

				if (a.equals("00") || a.equals("")) {

					Signals5.setIcon(new ImageIcon(getClass().getResource("images/NoSignalBar1.png")));
					Signals5.setBounds(665, -10, 80, 80);
					Signals5.setVisible(true);

				}
				if (a.equals("01") || a.equals("02")) {
					Signals5.setIcon(new ImageIcon(getClass().getResource("images/SignalBar1.png")));
					Signals5.setBounds(665, -12, 80, 80);
					Signals5.setVisible(true);

				}
				if (a.equals("03") || a.equals("04")) {

					Signals5.setIcon(new ImageIcon(getClass().getResource("images/SignalBar2.png")));
					Signals5.setBounds(666, -21, 80, 80);
					Signals5.setVisible(true);

				}
				if (a.equals("05") || a.equals("06")) {

					Signals5.setIcon(new ImageIcon(getClass().getResource("images/SignalBar3.png")));
					Signals5.setBounds(665, -17, 80, 80);
					Signals5.setVisible(true);

				}
				if (a.equals("07") || a.equals("08")) {

					Signals5.setIcon(new ImageIcon(getClass().getResource("images/SignalBar4.png")));
					Signals5.setBounds(665, -17, 80, 80);
					Signals5.setVisible(true);

				}
				if (a.equals("09") || a.equals("10") || a.equals("11") || a.equals("12") || a.equals("13") || a.equals("14")) {

					Signals5.setIcon(new ImageIcon(getClass().getResource("images/SignalBar4.png")));
					Signals5.setBounds(665, -17, 80, 80);
					Signals5.setVisible(true);
				}
			}
		};

		// Timer for changing the signal icon
		Timer timer = new Timer(1000, actionListener1);
		timer.start();

		// Adding all the Labels to the JPanel
		NaviPanel.add(levelAboveSea);
		NaviPanel.add(distanceGeo);
		NaviPanel.add(fb);
		NaviPanel.add(tw);
		NaviPanel.add(Lat);
		NaviPanel.add(Lon);
		NaviPanel.add(Time);
		NaviPanel.add(positionB);
		NaviPanel.add(placeMarkB);
		NaviPanel.add(naviReturnB);
		NaviPanel.add(journalB);
		NaviPanel.add(volumeON);
		NaviPanel.add(volumeOF);
		NaviPanel.add(proximityON);
		NaviPanel.add(proximityOFF);
		NaviPanel.add(backB);
		NaviPanel.add(backBG);
		NaviPanel.add(NaviPanelN);
		NaviPanel.add(NaviPanelD);

		return NaviPanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see MainMenu#getMainMenuPanel()
	 */
	@Override
	public JPanel getMainMenuPanel() {
		return mainMenuPanel;
	}

	/**
	 * sets the mainMenuPanel
	 * 
	 * @param mainMenuPanel
	 */
	public void setMainMenuPanel(JPanel mainMenuPanel) {
		this.mainMenuPanel = mainMenuPanel;
	}

	/**
	 * @return mainObj
	 */
	public MainMenu getMainObj() {
		return mainObj;
	}

	/**
	 * @param mainObj
	 */
	public void setMainObj(MainMenu mainObj) {
		this.mainObj = mainObj;
	}
}
