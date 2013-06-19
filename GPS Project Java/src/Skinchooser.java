import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * The class implements the skin chooser in the settings screen, which allowes a
 * user to change the between two available skins in the NaviScreen
 * 
 * @author Zarif Jawad
 * 
 */
public class Skinchooser extends MainMenu {

	JLabel jlbPicture;

	/**
	 * The method creates a frame where user can choose the skin
	 */
	public void jradiobutton() {
		final JFrame frame = new JFrame("Mode Chooser");
		frame.setSize(425, 215);
		frame.setResizable(false);
		JButton okbutton = new JButton("OK");
		okbutton.setSize(50, 10);
		okbutton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});

		// Create the radio buttons and assign Keyboard shortcuts using
		// Mnemonics
		JRadioButton NightM = new JRadioButton("Night Mode");
		NightM.setSelected(true);

		JRadioButton DayM = new JRadioButton("DayMode");
		jlbPicture = new JLabel();

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(DayM);
		group.add(NightM);

		DayM.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ImageIcon helpIcon = new ImageIcon(getClass().getResource("images/dms.png"));
				jlbPicture.setIcon(helpIcon);
				Panelno = 0;
			}
		});

		NightM.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ImageIcon helpIcon1 = new ImageIcon(getClass().getResource("images/nms.png"));

				// Set up the picture label
				jlbPicture.setIcon(helpIcon1);
				Panelno = 1;
			}
		});

		ImageIcon helpIcon1 = new ImageIcon(getClass().getResource("images/nms.png"));
		jlbPicture.setIcon(helpIcon1);

		// Set the Default Image
		jlbPicture.setPreferredSize(new Dimension(177, 122));

		// Put the radio buttons in a column in a panel
		JPanel jplRadio = new JPanel();
		jplRadio.setLayout(new GridLayout(0, 1));
		jplRadio.add(DayM);
		jplRadio.add(NightM);
		jplRadio.add(okbutton);

		frame.setLayout(new BorderLayout());
		frame.add(jplRadio, BorderLayout.WEST);
		frame.add(jlbPicture, BorderLayout.CENTER);
		jplRadio.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		frame.setVisible(true);
	}
}
