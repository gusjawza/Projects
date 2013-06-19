import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.google.code.facebookapi.FacebookException;

/**
 * The class Fb is used to display the GUI of facebook feature.
 * 
 * @author Zarif Jawad
 * @author Ionut Trancioveanu
 * @author David Giorgidze
 */
public class Fb {

	/**
	 * The method show is used to create and display the JFrame of a facebook
	 * feature, and checking the internet connection, then passing the user
	 * input to SendtoFacebook Class. and showing the result of the operation.
	 */
	public void show() {
		final JFrame frame = new JFrame();
		frame.setSize(430, 300);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		ImageIcon FBb = new ImageIcon(getClass().getResource("images/Facebook.png"));
		JLabel fBb = new JLabel(FBb);
		fBb.setBounds(0, 0, 440, 300);

		frame.setResizable(false);
		final JTextArea Field = new JTextArea("What's on your mind ?");
		Field.setToolTipText("What's on your mind ?");
		Field.setFont(new Font("Serif", Font.BOLD, 14));
		FocusListener listener = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				Field.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
			}

			private void dumpInfo(FocusEvent e) {
			}
		};
		Field.addFocusListener(listener);
		Field.setBounds(40, 90, 340, 110);

		frame.setVisible(true);
		JButton Exit = new JButton("Back");

		Exit.setBounds(300, 230, 80, 30);
		Exit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				frame.setVisible(false);
				frame.dispose();

			}
		});
		JButton Share = new JButton("Share");

		Share.setBounds(200, 230, 80, 30);
		Share.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				InternetCheck internetCheck = new InternetCheck();
				if (internetCheck.isInternetReachable() == true) {
					SendtoFacebook sf = new SendtoFacebook();
					try {
						sf.send(Field.getText());
						System.out.println("Upate to facebook");
					} catch (FacebookException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.setVisible(false);
					frame.dispose();

				} else if (internetCheck.isInternetReachable() == false) {
					JOptionPane.showMessageDialog(frame, "No internet connectivity");
					frame.setVisible(false);
					frame.dispose();
				}
			}
		});
		panel.add(Share);
		panel.add(Field);
		panel.add(Exit);
		panel.add(fBb);
		frame.add(panel);
		Exit.requestFocus();
	}
}
