import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class GmailQuickstart {


    public static void main(String... args) throws GeneralSecurityException, IOException, MessagingException, InterruptedException {
        GmailHelper gmailHelper = new GmailHelper("Reset password").executeQueryWithWaitingTime();
    }
}