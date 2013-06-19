package Map;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * This class is a panel for displaying the street view.
 * 
 * @author Paulius Vysniauskas
 * 
 */
public class StreetView extends JPanel {

	private int fbImageIndex = 0;
	private int lrImageIndex = 0;
	private int coordX, x1, x2, panelWidth;
	private BufferedImage[] forwardImages = new BufferedImage[6];
	private BufferedImage[] backwardImages = new BufferedImage[6];
	private BufferedImage[] leftImages = new BufferedImage[3];
	private BufferedImage[] rightImages = new BufferedImage[3];
	private JLabel arrowUp;
	private JLabel arrowDown;
	private JLabel arrowLeft;
	private JLabel arrowRight;
	private JLabel exitButton;
	private JLabel exitButton1;
	private boolean forward , back, left,right;
	public JLayeredPane jlp;
	public JPanel thisPanel;
	
	/**
	 * Default StreetView Constructor
	 * @param jlp
	 */
	public StreetView(JLayeredPane jlp) {
		this.jlp = jlp;
		for (int i = 0; i < 6; i++) {
			URL url = getClass().getResource(("/streetViewImages/f" + (i + 1) + ".jpg"));
			BufferedImage image = null;
			try {
				image = ImageIO.read(url);
			} catch (IOException e) {
			}
			forwardImages[i] = image;
		}

		for (int i = 0; i < 6; i++) {
			URL url = getClass().getResource(("/streetViewImages/b" + (i + 1) + ".jpg"));
			BufferedImage image = null;
			try {
				image = ImageIO.read(url);
			} catch (IOException e) {
			}
			backwardImages[i] = image;
		}
		
		for (int i = 0; i < 3; i++) {
			URL url = getClass().getResource(("/streetViewImages/l" + (i + 1) + ".jpg"));
			BufferedImage image = null;
			try {
				image = ImageIO.read(url);
			} catch (IOException e) {
			}
			leftImages[i] = image;
		}
		for (int i = 0; i < 3; i++) {
			URL url = getClass().getResource(("/streetViewImages/r" + (i + 1) + ".jpg"));
			BufferedImage image = null;
			try {
				image = ImageIO.read(url);
			} catch (IOException e) {
			}
			rightImages[i] = image;
		}
		forward = true;
		back = false;
		left = false;
		right = false;
		this.setBounds(0, 60,  820, 415);
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder (Color.blue, 2));
		panelWidth = this.getWidth();
		coordX = -((Math.abs(580 - 2000)) / 2);
		Cursor c = new Cursor(Cursor.HAND_CURSOR);
		arrowLeft = new JLabel(new ImageIcon(getClass().getResource("/streetViewImages/arrowLeft.png")));
		arrowLeft.setBounds(0,200, 70, 30);
		arrowLeft.setVisible(false);
		arrowLeft.addMouseListener(new MouseLabelClickHandler());
		add(arrowLeft);
		arrowRight = new JLabel(new ImageIcon(getClass().getResource("/streetViewImages/arrowRight.png")));
		arrowRight.setBounds(745,200, 70, 30);
		arrowRight.setVisible(false);
		arrowRight.addMouseListener(new MouseLabelClickHandler());
		add(arrowRight);
		arrowUp = new JLabel(new ImageIcon(getClass().getResource("/streetViewImages/arrowUp.png")));
		arrowUp.setBounds(395, 320, 70, 30);
		arrowUp.setVisible(true);
		arrowUp.addMouseListener(new MouseLabelClickHandler());
		add(arrowUp);
		arrowDown = new JLabel(new ImageIcon(getClass().getResource("/streetViewImages/arrowDown.png")));
		arrowDown.setBounds(390, 350, 80, 50);
		arrowDown.setVisible(true);
		arrowDown.addMouseListener(new MouseLabelClickHandler());
		add(arrowDown);
		exitButton = new JLabel(new ImageIcon(getClass().getResource("/streetViewImages/CloseButtonTransparency.png")));
		exitButton.setBounds(765, 5, 50, 50);
		exitButton.setVisible(true);
		exitButton.addMouseListener(new MouseLabelClickHandler());
		add(exitButton);
		exitButton1 = new JLabel(new ImageIcon(getClass().getResource("/streetViewImages/CloseButton.png")));
		exitButton1.setBounds(765, 5, 50, 50);
		exitButton1.setVisible(false);
		add(exitButton1);
		addMouseMotionListener(new MouseMotionHandler());
		addMouseListener(new MouseClickHandler());
		thisPanel = this;
		repaint();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (forward) {
			g2.drawImage(forwardImages[fbImageIndex], coordX, 0, this);
		}
		else if (back) {
			g2.drawImage(backwardImages[fbImageIndex], coordX, 0, this);
		}
		else if(left){
			g2.drawImage(leftImages[lrImageIndex], coordX, 0, this);
		}
		else if(right){
			g2.drawImage(rightImages[lrImageIndex], coordX, 0, this);
		}
	}
	

	/**
	 * Inner class Handler the mouse motion
	 * @author Paulius Vysniauskas 
	 *
	 */
	class MouseMotionHandler extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			x2 = e.getX();
			int dx = x2 - x1;
			x1 = x2;
			if ((coordX + dx) <= 0 && (coordX + dx) >= -(forwardImages[fbImageIndex].getWidth(null) - panelWidth))
				coordX += dx;
			repaint();
		}
	}
	/**
	 * Inner class Handler the mouse motion
	 * @author Paulius Vysniauskas 
	 *
	 */
	class MouseClickHandler implements MouseListener{
		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			x1 = e.getX();
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
	}
	
	/**
	 * Inner class Handler the mouse click
	 * @author Paulius Vysniauskas 
	 *
	 */
	class MouseLabelClickHandler implements MouseListener{
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			exitButton1.setVisible(false);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			exitButton1.setVisible(true);
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			JLabel source = (JLabel) arg0.getSource();
			coordX = -((Math.abs(580 - 2000)) / 2);
			if (source.equals(arrowUp)) {
				if (fbImageIndex < 5 && forward) {
					fbImageIndex++;
				} else if (fbImageIndex > 0 && back) {
					fbImageIndex--;
				} else if (lrImageIndex < 2 && left) {
					lrImageIndex++;
				} else if (lrImageIndex > 0 && right) {
					lrImageIndex--;
				}
				if((fbImageIndex == 3 && (forward || back)) || (lrImageIndex == 1 && (left || right))){
					arrowRight.setVisible(true);
					arrowLeft.setVisible(true);
				} else {
					arrowRight.setVisible(false);
					arrowLeft.setVisible(false);
				}
				repaint();
			}
			if (source.equals(arrowDown)) {
				if (forward || back) {
					forward = !forward;
					back = !back;
				} else if (left || right) {
					left = !left;
					right = !right;
				}
				repaint();
			}
			if (source.equals(arrowLeft)) {
				if(forward){
					forward = false;
					left = true;
					lrImageIndex = 1;
				} else if(back){
					back = false;
					right = true;
					lrImageIndex = 1;
				} else if(left){
					back = true;
					left = false;
				} else if(right){
					forward = true;
					right = false;
				}
				repaint();
			}
			if (source.equals(arrowRight)) {
				if(forward){
					forward = false;
					right = true;
					lrImageIndex = 1;
				} else if(back){
					back = false;
					left = true;
					lrImageIndex = 1;
				} else if(right){
					back = true;
					right = false;
				} else if(left){
					forward = true;
					left = false;
				}
				repaint();
			}
			if (source.equals(exitButton)) {
				jlp.remove(thisPanel);
			}
		}
	}
	
}
