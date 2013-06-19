import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The class GeocaheScreen is used to create and display the Geocache screen,
 * which is used to load the LOC file in the software.
 * 
 * @author Ionut Trancioveanu
 * @author Zarif Jawad
 * @author David Giorgidze
 */
public class GeocaheScreen implements DropTargetListener {

	DropTarget droptarget;
	JTextArea ta;

	public JLabel geocacheBackButton;
	public JLabel geocacheLoadButton;
	public JLabel geocacheGeolistButton;
	public final JPanel mainMenuPanel;
	public String LocFile = "";
	public LocParser parser;
	ArrayList<Map.GeoLocation> geoLocationList;

	GeocaheScreen(JPanel mainMenuPanel) {
		this.mainMenuPanel = mainMenuPanel;
		geoLocationList = new ArrayList<Map.GeoLocation>();
	}

	/**
	 * The method returns a JPanel containing all the buttons on the Geocache menu screen
	 * and also implements drag n' drop feature in the Geocache screen. Method also calls the LocParser class
	 * to parse the LOC file and it displays the list of Geocaches from the LOC file. 
	 * @return GeocachePanel
	 */
	public JPanel GeocacheScreenDisplay() {

		final JPanel GeocachePanel = new JPanel();
		GeocachePanel.setSize(1024, 640);
		GeocachePanel.setLayout(new BorderLayout());

		final JFrame frame = new JFrame();
		frame.setSize(300, 300);

		// Adding Background for geocachePanel
		ImageIcon geocacheMenuBackground = new ImageIcon(getClass().getResource("images/GeocacheMenu.png"));
		final JLabel geocacheMenu = new JLabel(geocacheMenuBackground);
		geocacheMenu.setBounds(0, 0, 1024, 640);

		addWindowListener(new BasicWindowMonitor());
		droptarget = new DropTarget(geocacheMenu, this);

		// Creating and Adding Buttons to the GeocahePane

		ImageIcon geocacheBackIcon = new ImageIcon(getClass().getResource("images/BackButton.png"));
		final JLabel geocacheBackButton = new JLabel(geocacheBackIcon);
		geocacheBackButton.setBounds(630, 390, 160, 80);

		ImageIcon geocacheBackIconG = new ImageIcon(getClass().getResource("images/BackButtonGlow.png"));
		final JLabel geocacheBackButtonG = new JLabel(geocacheBackIconG);
		geocacheBackButtonG.setBounds(630, 390, 160, 80);
		geocacheBackButtonG.setVisible(false);

		geocacheBackButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent evt) {
				geocacheBackButtonG.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				geocacheBackButtonG.setVisible(true);

			}
		});

		ImageIcon geocacheLoadIcon = new ImageIcon(getClass().getResource("images/LoadFileButton.png"));
		final JLabel geocacheLoadButton = new JLabel(geocacheLoadIcon);
		geocacheLoadButton.setBounds(350, 290, 260, 110);
		final JFileChooser fc = new JFileChooser();

		geocacheLoadButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent evt) {

				LocFile = fc.getSelectedFile().getAbsolutePath();
				parser = new LocParser();
				parser.FilePath = LocFile;
				parser.run();
				geoLocationList = parser.getGeoLocationList();
			}
		});

		// Create a JFileChooser with the current button

		ImageIcon geocacheGeolistIcon = new ImageIcon(getClass().getResource("images/GeoListButton.png"));
		final JLabel geocacheGeolistButton = new JLabel(geocacheGeolistIcon);
		geocacheGeolistButton.setBounds(560, 290, 260, 110);

		geocacheGeolistButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent evt) {
				final Object[] msg = new Object[geoLocationList.size()];
				if (!geoLocationList.isEmpty())
					for (int i = 0; i < geoLocationList.size(); i++) {
						String temp = geoLocationList.get(i).getName();
						msg[i] = temp;
					}
				JOptionPane
						.showInputDialog(null, "Take a look at the geocache list", "GeoList", JOptionPane.QUESTION_MESSAGE, null, msg, " LocFile ");
			}
		});

		// Back Button.addActionListener(this);
		geocacheBackButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				GeocachePanel.setVisible(false);
				mainMenuPanel.setVisible(true);
			}
		});

		GeocachePanel.add(geocacheLoadButton);
		GeocachePanel.add(geocacheGeolistButton);
		GeocachePanel.add(geocacheBackButton);
		GeocachePanel.add(geocacheBackButtonG);
		GeocachePanel.add(geocacheMenu);

		return GeocachePanel;
	}

	private void addWindowListener(BasicWindowMonitor basicWindowMonitor) {

	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		try {

			Transferable tr = dtde.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				System.out.println("Possible flavor: " + flavors[i].getMimeType());
				if (flavors[i].isFlavorJavaFileListType()) {
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					List list = (List) tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {

						String a = list.get(j).toString();

						LocFile = a;
						parser = new LocParser();
						parser.FilePath = LocFile;
						parser.run();
						geoLocationList = parser.getGeoLocationList();

					}
					dtde.dropComplete(true);
					return;
				}

				else if (flavors[i].isFlavorSerializedObjectType()) {
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					ta.setText("Successful text drop.\n\n");
					Object o = tr.getTransferData(flavors[i]);
					ta.append("Object: " + o);
					dtde.dropComplete(true);
					return;
				} else if (flavors[i].isRepresentationClassInputStream()) {
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					ta.setText("Successful text drop.\n\n");
					ta.read(new InputStreamReader((InputStream) tr.getTransferData(flavors[i])), "from system clipboard");
					dtde.dropComplete(true);
					return;
				}
			}
			System.out.println("Drop failed: " + dtde);
			dtde.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			dtde.rejectDrop();
		}
	}
}
