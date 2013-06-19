package Map;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
/**
 * This class is a panel for showing the street view icon.
 * 
 * @author Paulius Vysniauskas
 * 
 */
public class StreetViewIcon extends JPanel {

	public JLabel streetViewIcon;
	public double lon = 11.9373, lat = 57.7066;
	public int x, y;
	public JLayeredPane jlp;
	
	/**
	 * The default constructor
	 * @param width for the panel to be created.
	 * @param height for the panel to be created.
	 * @param layeredPane The maps layeredPane.
	 */
	public StreetViewIcon(int width, int hight, JLayeredPane layeredPane) {
		setBounds(0, 0, width, hight);setLayout(null);
		setOpaque(false);
		this.jlp = layeredPane;
		streetViewIcon = new JLabel(new ImageIcon(getClass().getResource("/streetViewImages/arrowIcon.png")));
		streetViewIcon.setBounds(x, y, 20, 24);
		streetViewIcon.setVisible(true);
		streetViewIcon.addMouseListener(new StreetViewIconListener());
		this.add(streetViewIcon);
	}
	/**
	 * An inner class for MouseListener.
	 * @author Paulius Vysniauskas
	 */
	class StreetViewIconListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
			jlp.add(new StreetView(jlp), new Integer(14));
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}

	}
	
}
