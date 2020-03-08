package mailingtask;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	private static final String initialFileName = "emailAdresses.txt";
	
	public static void main(String[] args) throws InterruptedException {
		EmailSender sender = new EmailSender();
		sender.setProperties(initialFileName);
		sender.setEmailsAmount(100);
		new Thread(sender).start();
	}
}

