package mailingtask;


import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * Represents the fake email sending.
 */
public class EmailSender implements Runnable {
	private String recipientEmail;

	@Override
	public void run() {
		try {

			// Task: fake the email sending waiting half a second
			Thread.sleep(500);
			System.out.print("sending...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.send();
	}

	/*
	 * Sets the recipient(s) of a message.
	 */
	public void setProperties(String email) {
		this.recipientEmail = email;
	}

	/*
	 * Creates a session for the sender. Sets the Subject, body of the message.
	 * Prints out the message to the console in case of successful uninterrupted
	 * message delivery.
	 */
	public void send() {

		try {
			// create a message
			Session session = createSession();
			MimeMessage msg = new MimeMessage(session);
			setRecipients(msg, recipientEmail);
			msg.setFrom(new InternetAddress("test@gmail.com"));
			msg.setSubject("Code challenge accepted.");
			msg.setSentDate(new Date());
			String msgBody = "Hello! Here's a message for you! Have a nice day!";
			msg.setText(msgBody);
			// Transport.send(msg);
			System.out.println("Successfully sent");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Create the properties for the successful session with a gmail host.
	 * @return session The created authenticated session with a particular host &
	 * port.
	 */
	public Session createSession() {
		// email & password of the sender
		final String gmail = "test@gmail.com";
		final String password = "123456789ABCDEF";
		// host for gmail
		String host = "smtp.gmail.com";
		int portSSL = 465;
		// create properties
		Properties props = System.getProperties();
		// and set smtp server
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.required", "true");
		// enable ssl protocol
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.port", portSSL);
		props.put("mail.smtp.host", host);

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(gmail, password);
			}
		});
		return session;
	}

	/*
	 * Set the recipient list for the sender and create new e-mail addresses.
	 * @param msg The message to be set to the recipient list.
	 * @param recipient The recipient list. If more than one recipient exists, then
	 * divide it by comma.
	 */
	public void setRecipients(MimeMessage msg, String recipient) throws Exception {
		//parses the string with email addresses divided by commas and adds the to the internet address list.
		InternetAddress[] addresses = InternetAddress.parse(recipient);
		msg.setRecipients(Message.RecipientType.TO, addresses);
	}
}