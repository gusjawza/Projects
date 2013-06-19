import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class creates and displays settings sub-menu panel and all of its components
 * 
 * @author David Giorgidze
 * @author Ionut Trancioveanu
 * @author Zarif Jawad
 * 
 */
public class SettingsScreen {

	public String unitSystem = "Metric";
	public final JPanel mainMenuPanel;

	/**
	 * sets the mainMenuPanel on the settings screen
	 * 
	 * @param mainMenuPanel
	 */
	SettingsScreen(JPanel mainMenuPanel) {
		this.mainMenuPanel = mainMenuPanel;
	}

	/**
	 * Method creates all the buttons and panel to display on the settings
	 * screen panel. it also allows the user to change the Skin and Units on
	 * NaviScreen from the settings menu.
	 * 
	 * @return settingsPanel
	 */
	public JPanel SettingsScreenPanel() {
		final JPanel settingsPanel = new JPanel();
		settingsPanel.setSize(1024, 640);
		settingsPanel.setLayout(new BorderLayout());
		settingsPanel.setVisible(false);
		ImageIcon settingsMenuBackground = new ImageIcon(getClass().getResource("images/MainMenuSettingsFinal.png"));
		final JLabel settingsMenu = new JLabel(settingsMenuBackground);
		settingsMenu.setVisible(true);
		settingsMenu.setBounds(0, 0, 0, 0);
		ImageIcon settingsLanguageIcon = new ImageIcon(getClass().getResource("images/Languages.png"));
		final JLabel settingsLanguageButton = new JLabel(settingsLanguageIcon);
		settingsLanguageButton.setBounds(230, 290, 271, 110);
		settingsLanguageButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showInputDialog(null, "Please choose a Language", "", JOptionPane.QUESTION_MESSAGE, null, new Object[] { "English",
						"Swedish", "Romana", "Lithuana", "Mongoliska", "Arabic", "Bengla", "Georgian" }, "");
			}
		});

		ImageIcon settingsModeIcon = new ImageIcon(getClass().getResource("images/ModeButton.png"));
		final JLabel settingsModeButton = new JLabel(settingsModeIcon);
		settingsModeButton.setBounds(500, 290, 271, 110);
		settingsModeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Skinchooser jb = new Skinchooser();
				jb.jradiobutton();
			}
		});

		ImageIcon settingsUnitsIcon = new ImageIcon(getClass().getResource("images/UnitsButton.png"));
		final JLabel settingsUnitsButton = new JLabel(settingsUnitsIcon);
		settingsUnitsButton.setBounds(370, 385, 260, 110);
		settingsUnitsButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JOptionPane jop = new JOptionPane();
				unitSystem = (String) jop.showInputDialog(null, "Please choose a Unit", "", jop.QUESTION_MESSAGE, null, new Object[] { "Metric",
						"Imperial" }, "");
			}
		});

		ImageIcon settingsBackIcon = new ImageIcon(getClass().getResource("images/BackButton.png"));
		final JLabel settingsBackButton = new JLabel(settingsBackIcon);
		settingsBackButton.setBounds(650, 410, 160, 80);

		ImageIcon settingsBackIconG = new ImageIcon(getClass().getResource("images/BackButtonGlow.png"));
		final JLabel settingsBackButtonG = new JLabel(settingsBackIconG);
		settingsBackButtonG.setBounds(650, 410, 160, 80);
		settingsBackButtonG.setVisible(false);

		settingsBackButton.addMouseListener(new MouseAdapter() {

			public void mouseExited(MouseEvent evt) {
				settingsBackButtonG.setVisible(false);
			}

			public void mouseEntered(MouseEvent evt) {
				settingsBackButtonG.setVisible(true);

			}
		});
		
		settingsBackButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				settingsPanel.setVisible(false);
				mainMenuPanel.setVisible(true);
			}
		});

		settingsPanel.add(settingsLanguageButton);
		settingsPanel.add(settingsModeButton);
		settingsPanel.add(settingsUnitsButton);
		settingsPanel.add(settingsBackButton);
		settingsPanel.add(settingsBackButtonG);
		settingsPanel.add(settingsMenu);

		return settingsPanel;
	}
}
