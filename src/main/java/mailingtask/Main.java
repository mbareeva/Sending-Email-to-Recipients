package mailingtask;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		EmailSender sender = new EmailSender();
		sender.setProperties("recipient@gmail.com, test@gmail.com");
		new Thread(sender).start();
	}
}

