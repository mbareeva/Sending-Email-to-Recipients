package mailingtask;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	public List<String> addresses = new ArrayList<String>();
	public static void main(String[] args) {

		String msgBody = "Hello! Here's a message for you! Have a nice day!";
		String host = "smtp.gmail.com";
		int portSSL = 465;
		// create properties and set smtp server
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.required", "true");
		//enable ssl protocol
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.port", portSSL);
		props.put("mail.smtp.host", host);
		// create default session
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				//insert gmail and password - prerequirement security services in google account
				return new PasswordAuthentication("*****@gmail.com", "***********");
			}
		});
		try {
			// create a message
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("******@gmail.com"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress("*****@gmail.com"));
			msg.setSubject("JavaMail hello world example");
			msg.setSentDate(new Date());
			msg.setText(msgBody);
			Transport.send(msg);
			System.out.println("Successfully sent");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void addRecipient(String email) {
		addresses.add(email);
	
	}
	
	public List<String> getRecipients(){
		return addresses;
	}
}

