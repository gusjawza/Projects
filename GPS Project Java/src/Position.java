import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Class displays the position information received from GPS in an uneditable
 * JTextField, that then user can copy the information if necessary
 * 
 * @author Paulius Vysniauskas
 * @author Zarif Jawad
 */
public class Position {

	/**
	 * Method takes object of the data and the mainMenu object as a parameter.
	 * It creates a JFrame which displays all the information provided above in
	 * class description
	 * 
	 * @param data
	 * @param mainMenu
	 */
	public void Position(Data data, MainMenu mainMenu) {

		JPanel panel = new JPanel();
		panel.setLayout(null);
		final JFrame frame = new JFrame("Position info");
		frame.setBounds(200, 200, 446, 280);
		frame.setResizable(false);
		frame.setVisible(true);
		ImageIcon pB = new ImageIcon(getClass().getResource("images/PositionBackground.png"));
		final JLabel pBack = new JLabel(pB);
		pBack.setBounds(0, 0, 446, 250);
		panel.add(pBack);
		JTextArea info = new JTextArea();
		info.setEditable(false);
		info.append("Lat  ________   " + data.getLatitude() + '\n' + "Lon  _________   " + data.getLongitude() + '\n');
		info.append("Elevation __________ " + data.getLevelAboveSea() + '\n' + "Date _______ " + data.getDate() + '\n' + "Time(GMT) _________  "
				+ data.getTime() + '\n' + "Operating System _________ " + data.getOs() + '\n');
		if (!mainMenu.map.selectedGeocacheName.equalsIgnoreCase(""))
			info.append("GeoCache _________  " + mainMenu.map.selectedGeocacheName);
		info.setBounds(34, 50, 265, 150);
		panel.add(info);

		JButton Exit = new JButton("Back");

		Exit.setBounds(335, 195, 90, 35);

		Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		panel.add(Exit);
		frame.add(panel);
	}
}
