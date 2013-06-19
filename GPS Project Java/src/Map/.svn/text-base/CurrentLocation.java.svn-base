package Map;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
/**
 * This class is a panel for current Location Point 
 * 
 * @author Paulius Vysniauskas
 */
public class CurrentLocation extends JPanel {

	private int xDiff, yDiff;
	private double lonPerPixel, latPerPixel, minLon, maxLat;
	public int currPosX = 0;
	public int currPosY = 0;
	public int greenFlagX = 0, greenFlagY = 0, redFlagX = 0, redFlagY = 0,
			rightClickX = 0, rightClickY = 0;
	public double greenFlagLat, greenFlagLon, redFlagLat, redFlagLon;
	private Image currPosPointer = Toolkit.getDefaultToolkit().getImage(getClass().getResource(
			"/images/PositionPoint.gif"));
	private Image greenFlagImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource
			("/images/GreenFlag.png"));
	private Image redFlagImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource(
			"/images/RedFlag.png"));
	private ImageIcon greenFlagIcon = new ImageIcon(getClass().getResource("/images/GreenFlag.png"));
	private ImageIcon redFlagIcon = new ImageIcon(getClass().getResource("/images/RedFlag.png"));
	private ImageIcon cancelFlagIcon = new ImageIcon(getClass().getResource(
			"/images/CancelFlag.png"));
	private boolean drawGreenFlag = false;
	private boolean drawRedFlag = false;
	private final JPopupMenu popupMenu;
	private JMenuItem menuItem;

	/**
	 * Constructor
	 * @param map
	 * @param width
	 * @param hight
	 */
	public CurrentLocation(final Map map, int width, int hight) {
		setBounds(0, 0, width, hight);
		setOpaque(false);
		popupMenu = new JPopupMenu();
		menuItem = new JMenuItem("Start", greenFlagIcon);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawGreenFlag = true;
				map.shortestPath.setVisible(false);
				drawRedFlag = false;
				greenFlagX = rightClickX;
				greenFlagY = rightClickY;
				greenFlagLon = (greenFlagX + xDiff) * (lonPerPixel) + (minLon);
				greenFlagLat = (maxLat) - ((greenFlagY + yDiff) * latPerPixel);
				repaint();
			}
		});
		popupMenu.add(menuItem);
		menuItem = new JMenuItem("Destination", redFlagIcon);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawRedFlag = true;
				redFlagX = rightClickX;
				redFlagY = rightClickY;
				redFlagLon = (redFlagX + xDiff) * (lonPerPixel) + (minLon);
				redFlagLat = (maxLat) - ((redFlagY + yDiff) * latPerPixel);
				map.flagsShortestPath(greenFlagLat, greenFlagLon, redFlagLat,
						redFlagLon);
			}
		});
		popupMenu.add(menuItem);
		menuItem = new JMenuItem("Reset", cancelFlagIcon);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				map.shortestPath.setVisible(false);
				drawGreenFlag = false;
				drawRedFlag = false;
				redFlagX = 0;
				redFlagY = 0;
				redFlagLon = 0;
				redFlagLat = 0;
				greenFlagX = 0;
				greenFlagY = 0;
				greenFlagLon = 0;
				greenFlagLat = 0;
			}
		});
		popupMenu.add(menuItem);
	}

	/**
	 * Shows the pop up menu according to the mouse positions
	 * @param comp
	 * @param i
	 * @param j
	 * @param xdiffPassed
	 * @param ydiffPassed
	 * @param lonPerPixelPassed
	 * @param latPerPixelPassed
	 * @param minlonPassed
	 * @param maxlatPassed
	 */
	public void showPopup(Component comp, int i, int j, int xdiffPassed,
			int ydiffPassed, double lonPerPixelPassed,
			double latPerPixelPassed, double minlonPassed, double maxlatPassed) {
		this.rightClickX = i;
		this.rightClickY = j;
		this.xDiff = xdiffPassed;
		this.yDiff = ydiffPassed;
		this.lonPerPixel = lonPerPixelPassed;
		this.latPerPixel = latPerPixelPassed;
		this.minLon = minlonPassed;
		this.maxLat = maxlatPassed;
		popupMenu.show(comp, rightClickX, rightClickY);
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (currPosX != 0 && currPosY != 0) {
			g2.drawImage(currPosPointer, currPosX - 18, currPosY - 18, this);
		}
		if (drawGreenFlag) {
			g2.drawImage(greenFlagImage, greenFlagX - 14, greenFlagY - 35, this);
		}
		if (drawRedFlag) {
			g2.drawImage(redFlagImage, redFlagX - 14, redFlagY - 35, this);
		}
	}
}
