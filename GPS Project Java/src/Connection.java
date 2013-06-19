import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * This class establishes the connection between the GPS device and the serial
 * port
 * 
 * @author Rated-R Coder: Rashid Darwish
 * @author Paulius Vysniauskas
 */
public class Connection implements SerialPortEventListener, Runnable {

	private Parser parse;
	private CommPort port;
	private InputStream input;
	private Data data;

	/**
	 * Connection constructor
	 * 
	 * @param data
	 */
	Connection(Data data) {
		this.data = data;
		parse = new Parser(data);
	}

	/**
	 * Locate available port name
	 * 
	 * @return port name, or null if no port identified
	 */
	private String checkPorts() {
		Enumeration ports = null;
		CommPortIdentifier port = null;
		ports = CommPortIdentifier.getPortIdentifiers();
		if (ports == null) {
			System.out.println("No PORTS!");
		}
		while (ports.hasMoreElements()) {
			port = (CommPortIdentifier) ports.nextElement();
			if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				data.setPort(port.getName());
			}
		}
		return port.getName();
	}

	/**
	 * Connect to the the port
	 */
	private void connectToPort() {
		try {
			port = CommPortIdentifier.getPortIdentifier(checkPorts()).open(
					"Connection", 4800);
			connecting();
		} catch (NoSuchPortException e) {
			System.out.println("NO SUCH PORT ");
		} catch (PortInUseException e) {
			System.out.println("Port already in use by " + e.currentOwner);

		} catch (Exception e) {
			System.err.println("GPS DEVICE IS NOT CONNECTED TO PORT ");
			try {
				Thread.sleep(3000);
				System.out.println("Reconnecting ");
				connectToPort();
			} catch (InterruptedException e1) {
				System.err.println("InterruptedException");
			}
		}
	}

	/**
	 * Cast the port to SerialPort and getting the input stream if the
	 * connection is established and the data is available
	 */
	private void connecting() {
		SerialPort devicePort = (SerialPort) port;
		try {
			devicePort.setSerialPortParams(4800, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			System.out.println("Connected!");
		} catch (UnsupportedCommOperationException e) {
			System.err.println("UnsupportedCommOperationException");
		}

		try {
			input = devicePort.getInputStream();
			devicePort.notifyOnDataAvailable(true);
			devicePort.addEventListener(this);
		} catch (Exception e) {
			System.err.println("Exception AT INPUT STREAM");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gnu.io.SerialPortEventListener#serialEvent(gnu.io.SerialPortEvent)
	 */
	@Override
	public void serialEvent(SerialPortEvent e) {
		int newData = 0;
		char ch = '0';
		StringBuffer inputBuffer;
		switch (e.getEventType()) {
		case SerialPortEvent.DATA_AVAILABLE:
			inputBuffer = new StringBuffer();
			while (ch != '\n') {
				try {
					newData = input.read();
					ch = (char) newData;
					if (newData == -1) {
						break;
					}
					inputBuffer.append((char) newData);
				} catch (IOException ex) {
					System.err.println("IOException");
					return;
				}
			}
			parse.parseNMEA(inputBuffer.toString());
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		data.setOs(System.getProperty("os.name"));
		connectToPort();
	}
}
