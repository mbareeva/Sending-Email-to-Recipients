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
	private int emailsAmount;

	@Override
	public void run() {
		try {
			// Faking the email sending waiting for 0.5 second.
			Thread.sleep(500);
			System.out.print("sending...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// set in the parameter the amount of mails to send.
		this.send(emailsAmount);
	}

	/**
	 * Set the amount of mails to send to the recipients.
	 * 
	 * @param n The amount of mails to be sent.
	 */
	public void setEmailsAmount(int n) {
		this.emailsAmount = n;
	}

	/**
	 * Sets the recipient(s) of a message.
	 * 
	 * @param email The list of recipient mails divided by the comma
	 */
	public void setProperties(String email) {
		this.recipientEmail = email;
	}

	/**
	 * Creates a session for the sender. Sets the Subject, body of the message. The
	 * message delivery is fake as the Transport.send() method is commented out.
	 * Instead the message will be printed out to the console in case of successful
	 * uninterrupted "fake" message delivery.
	 * 
	 * @param mailsCount The amount of mails to be send out.
	 */
	public void send(int mailsCount) {
		try {
			Session session = createSession();
			// in case of multiple emails, the message is going
			// to be send multiple times depending on the value of
			// mailsCount parameter.
			for (int i = 0; i < mailsCount; i++) {
				MimeMessage msg = new MimeMessage(session);
				setRecipients(msg, recipientEmail);
				msg.setFrom(new InternetAddress("test@gmail.com"));
				msg.setSubject("Code challenge accepted.");
				msg.setSentDate(new Date());
				String msgBody = "Hello! Here's a message for you! Have a nice day!";
				msg.setText(msgBody);
				Thread.sleep(500);
				System.out.println("Successfully sent");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the properties for the successful session with a gmail host.
	 * 
	 * @return The created authenticated session with a particular host & port.
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

	/**
	 * Set the recipient list for the sender and create new e-mail addresses.
	 * 
	 * @param msg       The message to be set to the recipient list.
	 * @param recipient The recipient list. If more than one recipient exists, then
	 *                  divide it by comma.
	 */
	public void setRecipients(MimeMessage msg, String recipient) throws Exception {
		// parses the string with email addresses divided by commas and adds the to the
		// Internet address list.
		InternetAddress[] addresses = InternetAddress.parse(recipient);
		msg.setRecipients(Message.RecipientType.TO, addresses);
	}
}