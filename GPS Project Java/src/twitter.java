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

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Class is used to update status on tweeter. it creates a GUI of it, and
 * establishes connection to the tweeter
 * 
 * @author Zarif Jawad
 * 
 */
public class twitter {
	public String CONSUMER_KEY = "Q3xqZ5utclbHBTaipOeJA";
	public String CONSUMER_SECRET = "oknyXldKAveHG7eqrxwTvTYOd3bxrKpdsxrBFqfdW4U";
	public String ACCESS_TOKEN = "275030351-RrUZX7aozrYmAQECfEI3Qy4ZabuxfyscN04ffcTA";
	public String ACCESS_TOKEN_SECRET = "5rIA4wqKASs2Exw4zqfFSCjwGnrN2WU7GrjSk4FyFg";

	/**
	 * GUI of a tweeter
	 */
	public void popup() {
		final JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		frame.setSize(446, 250);
		frame.setResizable(false);

		ImageIcon TWb = new ImageIcon(getClass().getResource(
				"images/tweeter.png"));
		final JLabel twb = new JLabel(TWb);
		twb.setBounds(0, 0, 446, 250);

		JButton Exit = new JButton("Back");

		Exit.setBounds(330, 190, 80, 30);
		Exit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				frame.setVisible(false);
				frame.dispose();

			}
		});
		JButton Tweet = new JButton("Tweet");
		final JTextArea Field = new JTextArea("What’s happening?");
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
		Field.setBounds(180, 82, 230, 88);
		Tweet.setBounds(230, 190, 80, 30);
		Tweet.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				InternetCheck internetCheck = new InternetCheck();
				if (internetCheck.isInternetReachable() == true) {
					try {
						sendtweet(Field.getText(), frame);
					} catch (TwitterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("Tweet status updated ");
					frame.setVisible(false);
					frame.dispose();
				} else if (internetCheck.isInternetReachable() != true) {
					JOptionPane.showMessageDialog(frame,
							"Not connected to Internet");
					frame.setVisible(false);
					frame.dispose();
				}
			}
		});

		panel.add(Field);
		panel.add(Tweet);
		panel.add(Exit);
		panel.add(twb);
		frame.add(panel);
		frame.setVisible(true);
		Exit.requestFocus();

	}

	/**
	 * The takes a user input and a JFrame as a parameter and updates users
	 * status on default tweeter account
	 * 
	 * @param msg
	 * @param frame
	 * @throws TwitterException
	 */
	public void sendtweet(String msg, JFrame frame) throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(CONSUMER_KEY);
		cb.setOAuthConsumerSecret(CONSUMER_SECRET);
		twitter4j.conf.Configuration config = cb.build();
		TwitterFactory factory = new TwitterFactory(config);

		AccessToken acToken = new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
		Twitter twitter = factory.getInstance(acToken);
		twitter.updateStatus(msg);
		JOptionPane.showMessageDialog(frame, "Twetter status updated");

	}
}
