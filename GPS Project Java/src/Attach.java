import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * The class Attach is used to send an Email with an attachment.
 * 
 * @author Zarif Jawad
 */

public class Attach {

	public String toemail;
	public String mssg;
	public String sub;

	/**
	 * The method sendMail takes location of a file as a parameter and sends an
	 * Email to the address set by the user from the default Email address.
	 * 
	 * @param FileLocation
	 * @throws Exception
	 */
	public void sendMail(String FileLocation) throws Exception {

		String host = "smtp.gmail.com";
		String from = "navigps.itu@gmail.com";
		String to = toemail;
		String fileAttachment = FileLocation;
		Properties props = System.getProperties();
		props.put("smtp.gmail.com", host);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("navigps.itu@gmail.com", "navibdiprz");
			}
		});
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(sub);
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(mssg);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		if (fileAttachment != "") {
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileAttachment);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileAttachment);
			multipart.addBodyPart(messageBodyPart);
		}
		message.setContent(multipart);
		Transport.send(message);
	}
}