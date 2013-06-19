import javax.swing.JFrame;

/**
 * The Main class creates the main frame
 * 
 * @author Rated-R Coder: Rashid Darwish
 * 
 */
public class Main {

	private SplashScreen splash;
	private MainMenu mainMenu;
	private JFrame frame;

	/**
	 * Main constructor
	 */
	Main() {
		splash = new SplashScreen(2000);
		splash.showSplash();
		frame = new JFrame();
		frame.setSize(1024, 640);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final NaviScreen naviScreenObj = new NaviScreen();
		mainMenu = new MainMenu(splash.getMap(), splash.getControl().getData(),
				naviScreenObj);
		naviScreenObj.setMainMenuPanel(mainMenu.mainMenuPanel);
		naviScreenObj.setMainObj(mainMenu);
		mainMenu.run(frame);
		while (true) {
			mainMenu.setCoordents();
		}
	}

	/**
	 * The main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}
}
