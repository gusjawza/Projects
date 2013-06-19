import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * The Notes class creates and displays a JFrame from where user can send an
 * Email with an attachment or without it. Class also allows the user to save
 * and open the text documents. The class implements drag n' drop for
 * attachments.
 * 
 * @author Ionut Trancioveanu
 * @author Zarif Jawad
 * 
 */
public class Notes implements DropTargetListener {
	public JTextField subtext;
	public JTextField NoteText;
	public JTextField Emailtext;
	public DropTarget droptarget;
	JTextArea ta;
	public String a;
	public String FileAttach = "";

	/**
	 * The method start creates a JFrame when accessed and creates and puts all
	 * the buttons and labels. The method also sends an email to the email
	 * address provided by the user. It also allows the user to choose
	 * attachement from a JFilechooser. The method also allows the user to save
	 * the input and open them in notepad.
	 */
	public void start() {

		final JFileChooser c = new JFileChooser();
		c.addChoosableFileFilter(new MyFilter());

		final JFileChooser b = new JFileChooser();

		final JFrame frame1 = new JFrame();
		frame1.setTitle("Notes");
		frame1.setSize(535, 530);
		frame1.setBounds(300, 100, 535, 530);
		frame1.setResizable(false);

		ImageIcon Drop = new ImageIcon(getClass().getResource("images/JournalDrop.png"));
		final JLabel drop = new JLabel(Drop);
		drop.setBounds(110, 280, 400, 200);

		ImageIcon Title = new ImageIcon(getClass().getResource("images/MyJournalLogo.png"));
		final JLabel title = new JLabel(Title);
		title.setBounds(200, 15, 140, 50);
		ImageIcon Case = new ImageIcon(getClass().getResource("images/JournalCase.png"));
		final JLabel journal = new JLabel(Case);
		Border thinBorder = LineBorder.createBlackLineBorder();
		journal.setBorder(thinBorder);
		journal.setBounds(0, 0, 530, 530);
		JLabel sublabel = new JLabel("Subject");
		sublabel.setBounds(50, 30, 80, 30);
		final JTextField subtext = new JTextField();

		subtext.setBorder(thinBorder);
		subtext.setBounds(50, 70, 450, 30);
		JLabel Notelabel = new JLabel("Note");

		Notelabel.setBounds(50, 100, 80, 30);
		final JTextArea NoteText = new JTextArea();
		NoteText.setBorder(thinBorder);
		NoteText.setBounds(50, 130, 450, 130);
		JLabel Emaillabel = new JLabel("Email  /  (Optional)");

		JButton SendAttachment = new JButton("Attach");
		// SendAttachment.setBorder(roundedBorder);
		SendAttachment.setBounds(50, 340, 80, 25);
		SendAttachment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					int returnVal = b.showOpenDialog(frame1);
					FileAttach = b.getSelectedFile().getAbsolutePath();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		Emaillabel.setBounds(50, 265, 200, 30);
		final JTextField Emailtext = new JTextField();
		Emailtext.setBorder(thinBorder);
		Emailtext.setBounds(50, 300, 450, 30);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JButton Open = new JButton("Open");
		Border roundedBorder = new LineBorder(Color.black, 6, true);
		Open.setBorder(roundedBorder);
		Open.setBounds(50, 450, 80, 30);

		JButton Save = new JButton("Save");
		Save.setBorder(roundedBorder);
		Save.setBounds(160, 450, 80, 30);

		JButton Email = new JButton("Send Email");
		Email.setBorder(roundedBorder);
		Email.setBounds(270, 450, 120, 30);

		JButton Exit = new JButton("Exit");
		Exit.setBorder(roundedBorder);
		Exit.setBounds(420, 450, 80, 30);
		Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				frame1.dispose();
			}
		});

		Email.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Emailtext.getText().equals("") || subtext.getText().equals("") || NoteText.getText().equals("")) {
					JOptionPane.showMessageDialog(frame1, "Please Fill up all the fields");
				}

				else if (Emailtext.getText().isEmpty() != true && subtext.getText().isEmpty() != true && NoteText.getText().equals("") != true) {
					InternetCheck internetCheck = new InternetCheck();
					if (internetCheck.isInternetReachable() == true) {
						Attach attach = new Attach();
						try {
							attach.sub = subtext.getText();
							attach.toemail = Emailtext.getText();
							attach.mssg = NoteText.getText();
							attach.sendMail(FileAttach);
							JOptionPane.showMessageDialog(frame1, "Email sent successfully");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else if (internetCheck.isInternetReachable() == false) {
						JOptionPane.showMessageDialog(frame1, "Internet not reachable");
						frame1.setVisible(false);
						frame1.dispose();
					}
				}
			}
		});

		Save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = c.showSaveDialog(frame1);
				if (returnVal != JFileChooser.CANCEL_OPTION) {
					File file = c.getSelectedFile();
					try {
						String InputEmailtext = Emailtext.getText();
						String Inputsubjecttext = subtext.getText();
						String InputNoteText = NoteText.getText();
						FileWriter fstream = new FileWriter(file);
						BufferedWriter out = new BufferedWriter(fstream);
						System.out.println(Inputsubjecttext);
						System.out.println(InputNoteText);
						out.write("Email");
						out.newLine();
						out.newLine();
						out.write(InputEmailtext);
						out.newLine();
						out.newLine();
						out.write(" Subject");
						out.newLine();
						out.newLine();
						out.write(Inputsubjecttext);
						out.newLine();
						out.newLine();
						out.write("Note");
						out.newLine();
						out.newLine();
						out.write(InputNoteText);
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		Open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int returnVal = c.showOpenDialog(frame1);
				if (returnVal != JFileChooser.CANCEL_OPTION) {
					String Filepath = c.getSelectedFile().getAbsolutePath();
					String Test = Filepath.replace("\\", "/");
					String path = ("notepad " + Test);
					try {
						Runtime.getRuntime().exec(path);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		addWindowListener(new BasicWindowMonitor());
		droptarget = new DropTarget(drop, this);
		panel.add(drop);
		panel.add(SendAttachment);
		panel.add(title);
		panel.add(Emaillabel);
		panel.add(Emailtext);
		panel.add(Notelabel);
		panel.add(NoteText);
		panel.add(subtext);
		panel.add(sublabel);
		panel.add(Open);
		panel.add(Save);
		panel.add(Email);
		panel.add(Exit);
		panel.add(journal);
		frame1.add(panel);
		frame1.setVisible(true);

	}

	//Implementing the Drag n' Drop for attachment
	private void addWindowListener(BasicWindowMonitor basicWindowMonitor) {
	}

	@Override
	public void dragEnter(DropTargetDragEvent arg0) {
	}

	@Override
	public void dragExit(DropTargetEvent arg0) {
	}

	@Override
	public void dragOver(DropTargetDragEvent arg0) {
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
		try {

			Transferable tr = dtde.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				System.out.println("Possible flavor: " + flavors[i].getMimeType());

				if (flavors[i].isFlavorJavaFileListType()) {

					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					java.util.List list = (java.util.List) tr.getTransferData(flavors[i]);
					for (int j = 0; j < list.size(); j++) {
						System.out.println("<<<< " + list.get(j) + "\n");
						String a = list.get(j).toString();
						FileAttach = a;
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
				}
				else if (flavors[i].isRepresentationClassInputStream()) {
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
					ta.setText("Successful text drop.\n\n");
					ta.read(new InputStreamReader((InputStream) tr.getTransferData(flavors[i])), "from system clipboard");
					dtde.dropComplete(true);
					return;
				}
			}
			dtde.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			dtde.rejectDrop();
		}

	}

	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
	}
}
