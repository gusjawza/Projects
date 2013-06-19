import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Map.Map;

/**
 * MainMenu class creates and displays the main menu of software. which includes
 * all other sub-menus, as well as objects from other classes such as Data, Map,
 * NaviScreen.
 * 
 * 
 * @author David Giorgidze
 * @author Zarif Jawad
 * @author Ionut Trancioveanu
 */

public class MainMenu {

	public static int Panelno = 1;
	public JLabel mainframe;
	public JLabel Mainmenu;
	public JLabel settings;
	public JLabel MenuN;
	public JLabel MenuG;
	public JLabel MenuS;
	public JLabel MenuE;
	public JPanel mainMenuPanel;

	/**
	 * Method returns JPanel of main menu
	 * 
	 * @return mainMenuPanel
	 */
	public JPanel getMainMenuPanel() {
		return mainMenuPanel;
	}

	public SettingsScreen SettingsScreen;
	public JPanel SettingsPanel;
	public JPanel GeocachePanel;
	public JLabel NaviPanelBackground;
	public int Position = 0;
	public JPanel containerPanel;
	public JLayeredPane container;
	public Map map;
	public Data data;
	public NaviScreen naviScreenObj;
	public GeocaheScreen Geoscreen;

	/**
	 * Empty Constructor
	 */
	public MainMenu() {
	}

	/**
	 * Constructor for MainMenu class
	 * 
	 * @param map
	 * @param data
	 * @param naviScreenObj
	 */
	public MainMenu(Map map, Data data, NaviScreen naviScreenObj) {
		this.map = map;
		this.data = data;
		this.naviScreenObj = naviScreenObj;
	}

	/**
	 * The method is used to scroll between the sub-menus
	 */
	public void increasePosition() {
		Position++;
	}

	/**
	 * Method run is called from the Main class to integrate every sub-menus
	 * into the MainFrame. Create objects of all the sub-menus, creating and
	 * displaying all necessary buttons with graphical effects. The method adds
	 * map panel to the layered pane.
	 * 
	 * @param Frame
	 */
	public void run(JFrame Frame) {

		this.mainMenuPanel = new JPanel();

		JPanel containerPanel = new JPanel();

		JLayeredPane container = new JLayeredPane();

		// Getting Frame from Main

		final JFrame Frame1 = Frame;
		Frame.add(containerPanel);

		// Making object of Settings screen
		SettingsScreen = new SettingsScreen(mainMenuPanel);
		final JPanel SettingsPanel = SettingsScreen.SettingsScreenPanel();
		SettingsPanel.setVisible(false);

		// Making object of Geoscreen
		Geoscreen = new GeocaheScreen(mainMenuPanel);
		final JPanel GeocachePanel = Geoscreen.GeocacheScreenDisplay();
		GeocachePanel.setVisible(false);

		// Making an Object of NaviScreen
		final JPanel naviScreenPanel = naviScreenObj.NaviscreenDisplay();
		naviScreenPanel.setVisible(false);

		// Creating mainMenu Panel
		mainMenuPanel.setSize(1024, 640);
		mainMenuPanel.setLayout(new BorderLayout());
		mainMenuPanel.setVisible(true);

		// Creating and adding the NaviButton to the menu
		ImageIcon NaviMenu = new ImageIcon(getClass().getResource("images/NaviButton.png"));
		final JLabel MenuN = new JLabel(NaviMenu);

		ImageIcon NaviMenuGlow = new ImageIcon(getClass().getResource("images/NaviAnimation.gif"));
		final JLabel MenuNN = new JLabel(NaviMenuGlow);
		MenuNN.setVisible(false);

		mainMenuPanel.add(MenuNN);
		mainMenuPanel.add(MenuN);
		MenuN.setBounds(385, 250, 260, 250);
		MenuNN.setBounds(377, 226, 260, 250);

		// Creating and adding the GeocacheButton to the menu
		ImageIcon Geocache = new ImageIcon(getClass().getResource("images/GeocacheButton.png"));
		final JLabel MenuG = new JLabel(Geocache);

		ImageIcon GeocacheG = new ImageIcon(getClass().getResource("images/GeoAnimation.gif"));
		final JLabel MenuGG = new JLabel(GeocacheG);

		MenuGG.setVisible(false);
		MenuG.setVisible(false);
		mainMenuPanel.add(MenuGG);
		mainMenuPanel.add(MenuG);

		MenuG.setBounds(330, 253, 347, 260);
		MenuGG.setBounds(376, 290, 250, 125);

		// Creating and adding the SettingsButton to the menu
		ImageIcon SettingsS = new ImageIcon(getClass().getResource("images/SettingsAnimation.gif"));
		final JLabel MenuSS = new JLabel(SettingsS);

		ImageIcon Settings = new ImageIcon(getClass().getResource("images/SettingsButton.png"));
		final JLabel MenuS = new JLabel(Settings);

		MenuS.setVisible(false);
		MenuSS.setVisible(false);
		mainMenuPanel.add(MenuSS);
		mainMenuPanel.add(MenuS);
		MenuSS.setBounds(349, 225, 310, 260);
		MenuS.setBounds(350, 253, 310, 260);

		// Creating and adding the Exit Button
		ImageIcon ExitE = new ImageIcon(getClass().getResource("images/ExitAnimation.gif"));
		final JLabel MenuEE = new JLabel(ExitE);

		ImageIcon Exit = new ImageIcon(getClass().getResource("images/ExitButton.png"));
		final JLabel MenuE = new JLabel(Exit);
		MenuEE.setVisible(false);
		MenuE.setVisible(false);

		mainMenuPanel.add(MenuEE);
		mainMenuPanel.add(MenuE);
		MenuEE.setBounds(383, 231, 249, 252);
		MenuE.setBounds(390, 260, 250, 250);

		// Adding ActionListeners to Geocache Menu button
		MenuG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent evt) {
				MenuGG.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				MenuGG.setVisible(true);
				MenuG.setVisible(true);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				GeocachePanel.setVisible(true);
				mainMenuPanel.setVisible(false);
			}
		});

		// Adding actionListener to the Exit Button
		MenuE.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent evt) {
				MenuEE.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				MenuEE.setVisible(true);
				MenuE.setVisible(true);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		// Adding ActionListener to the Settings button
		MenuS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent evt) {
				MenuSS.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				MenuSS.setVisible(true);
				MenuS.setVisible(true);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				mainMenuPanel.setVisible(false);
				SettingsPanel.setVisible(true);
			}
		});

		// Adding ActionListener to the NaviButton
		MenuN.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				MenuNN.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				MenuNN.setVisible(false);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				mainMenuPanel.setVisible(false);

				if (Panelno == 0) {
					NaviScreen.NaviPanelN.setVisible(false);
					NaviScreen.NaviPanelD.setVisible(true);
				} else if (Panelno == 1) {
					NaviScreen.NaviPanelD.setVisible(false);
					NaviScreen.NaviPanelN.setVisible(true);
				}

				map.geocacheList = Geoscreen.geoLocationList;
				map.fillDropDownMenu();
				map.setVisible(true);
				naviScreenPanel.setVisible(true);
				map.requestFocus();
			}
		});
		ImageIcon aboutIcon = new ImageIcon(getClass().getResource("images/AboutButton.png"));
		final JLabel aboutButton = new JLabel(aboutIcon);

		ImageIcon AboutGlow = new ImageIcon(getClass().getResource("images/AboutButtonGlow.png"));
		final JLabel aboutButtonGlow = new JLabel(AboutGlow);

		final ImageIcon teamMembers = new ImageIcon(getClass().getResource("images/TeamMembers.png"));
		final JLabel TeamMembers = new JLabel(teamMembers);

		mainMenuPanel.add(aboutButtonGlow);
		mainMenuPanel.add(aboutButton);
		mainMenuPanel.add(TeamMembers);

		TeamMembers.setVisible(false);
		aboutButtonGlow.setVisible(false);

		aboutButtonGlow.setBounds(660, 130, 190, 160);
		TeamMembers.setBounds(200, 200, 800, 500);
		aboutButton.setBounds(660, 130, 190, 160);
		aboutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				aboutButtonGlow.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				aboutButtonGlow.setVisible(true);
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				JOptionPane.showMessageDialog(Frame1, new JLabel(teamMembers), " FrameWork Studio © 2011 ", JOptionPane.DEFAULT_OPTION);

			}

		});

		// Adding HelpButton
		ImageIcon helpIcon = new ImageIcon(getClass().getResource("images/HelpButton.png"));
		final JLabel helpButton = new JLabel(helpIcon);

		ImageIcon HelpButtonGlow = new ImageIcon(getClass().getResource("images/HelpButtonGlow.png"));
		final JLabel helpGlow = new JLabel(HelpButtonGlow);

		final ImageIcon helpmenu = new ImageIcon(getClass().getResource("images/HelpMenu.png"));
		final JLabel helpMenu = new JLabel(helpmenu);

		helpGlow.setVisible(false);
		helpGlow.setBounds(170, 110, 190, 170);
		helpMenu.setVisible(false);

		mainMenuPanel.add(helpMenu);
		mainMenuPanel.add(helpGlow);
		mainMenuPanel.add(helpButton);

		helpMenu.setBounds(0, 0, 1000, 500);
		helpButton.setBounds(170, 110, 190, 170);
		helpButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent evt) {
				helpGlow.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				helpGlow.setVisible(true);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(Frame1, new JLabel(helpmenu), " FrameWork Studio © 2011 ", JOptionPane.DEFAULT_OPTION);
			}
		});

		// Adding Satelite image
		ImageIcon sateliteIcon = new ImageIcon(getClass().getResource("images/Satelite.gif"));
		JLabel satelite = new JLabel(sateliteIcon);

		mainMenuPanel.add(satelite);
		satelite.setBounds(560, 160, 150, 155);

		// Adding the RightArrow
		ImageIcon arrowRigthIcon = new ImageIcon(getClass().getResource("images/ArrowRight.png"));
		JLabel arrowRightButton = new JLabel(arrowRigthIcon);

		ImageIcon ArrowRightGlow = new ImageIcon(getClass().getResource("images/ArrowRightGlow.png"));
		final JLabel arrowRightGlow = new JLabel(ArrowRightGlow);

		arrowRightGlow.setVisible(false);

		mainMenuPanel.add(arrowRightGlow);
		mainMenuPanel.add(arrowRightButton);

		arrowRightGlow.setBounds(665, 280, 150, 160);
		arrowRightButton.setBounds(665, 280, 150, 160);
		arrowRightButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				arrowRightGlow.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				arrowRightGlow.setVisible(false);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				increasePosition();

				if (Position == 1) {
					MenuN.setVisible(false);
					MenuG.setVisible(true);

				}
				if (Position == 2) {
					MenuG.setVisible(false);
					MenuS.setVisible(true);
				}
				if (Position == 3) {
					MenuS.setVisible(false);
					MenuG.setVisible(false);
					MenuN.setVisible(false);
					MenuE.setVisible(true);
				}
				if (Position == 4) {
					MenuS.setVisible(false);
					MenuG.setVisible(false);
					MenuE.setVisible(false);
					MenuN.setVisible(true);
					Position = 0;
				}
			}
		});

		// Adding Left Arrow
		ImageIcon arrowLeftIcon = new ImageIcon(getClass().getResource("images/ArrowLeft.png"));
		JLabel arrowLeftButton = new JLabel(arrowLeftIcon);

		ImageIcon ArrowLeftGlow = new ImageIcon(getClass().getResource("images/ArrowLeftGlow.png"));
		final JLabel arrowLeftGlow = new JLabel(ArrowLeftGlow);

		arrowLeftGlow.setVisible(false);

		mainMenuPanel.add(arrowLeftGlow);
		mainMenuPanel.add(arrowLeftButton);

		arrowLeftGlow.setBounds(190, 280, 150, 160);
		arrowLeftButton.setBounds(190, 280, 150, 160);

		arrowLeftButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				arrowLeftGlow.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				arrowLeftGlow.setVisible(true);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (Position == 0) {

					MenuE.setVisible(true);
					MenuS.setVisible(false);
					MenuG.setVisible(false);
					MenuN.setVisible(false);
					Position = 3;
				}
				if (Position == 1) {
					MenuN.setVisible(true);
					MenuG.setVisible(false);
					MenuS.setVisible(false);
					MenuE.setVisible(false);
					Position = 0;

				}
				if (Position == 2) {
					MenuG.setVisible(true);
					MenuS.setVisible(false);
					MenuE.setVisible(false);
					MenuN.setVisible(false);
					Position = 1;
				}
				if (Position == 3) {
					MenuS.setVisible(true);
					MenuG.setVisible(false);
					MenuN.setVisible(false);
					MenuE.setVisible(false);
					Position = 2;
				}
			}
		});

		// Adding the mainMenu Background
		ImageIcon mainMenuBackground = new ImageIcon(getClass().getResource("images/MainMenuFinalVersion.png"));
		final JLabel mainMenu = new JLabel(mainMenuBackground);

		mainMenu.setVisible(true);
		mainMenuPanel.add(mainMenu);
		mainMenu.setBounds(0, 0, 1024, 640);

		ImageIcon mainFrameBackground = new ImageIcon(getClass().getResource("images/Navi Menu FinalVersion copy.png"));
		JLabel mainFrame = new JLabel(mainFrameBackground);

		mainMenuPanel.add(mainFrame);
		mainFrame.setBounds(0, 0, 1024, 640);

		Frame1.setTitle(" Navi GPS 2011    Version 0.9.2");
		Frame1.setSize(1040, 678);
		Frame1.setLocationRelativeTo(null);

		container.add(mainMenuPanel, 1);
		container.add(SettingsPanel, 3);
		container.add(GeocachePanel, 2);
		container.add(naviScreenPanel, 4);
		container.add(map, 0);

		Frame1.setVisible(true);
		containerPanel.setVisible(true);
		containerPanel.setLayout(new BorderLayout());
		containerPanel.setSize(1024, 640);
		containerPanel.add(container);

	}

	/**
	 * hiding the map
	 */
	public void hideMap() {
		map.setVisible(false);
	}

	/**
	 * gets coordinates from GPS device and assigns this values in the map class
	 */
	public void setCoordents() {
		if (!data.getLongitude().equals("No Signal") && !data.getLongitude().equals("")) {
			try {
				map.setCurrentLoc(data.getLongitude(), data.getLatitude());
				Thread.sleep(2000);
			} catch (InterruptedException ex) {
				System.err.println("errrr");
			}
		}
	}
}