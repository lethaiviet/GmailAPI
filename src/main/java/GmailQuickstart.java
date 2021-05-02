import lombok.SneakyThrows;

import javax.mail.internet.MimeMessage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class GmailQuickstart {
    private static final String EMAIL_TEMPLATE = "src/main/resources/email_template.html";
    private static final String QUERY_FORMAT = "from:%s subject:%s after:%s";

    @SneakyThrows
    public static String readFileAsString(String fileName) {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static Long getCurrentTimestamp() {
        Date date = new Date();
        return date.getTime() / 1000L;
    }

    public static void main(String... args) {
        //test send email
        String to = "lethaiviet002@gmail.com";
        String from = "me";
        String subject = "[TEST] Send message";
        String timestamp = getCurrentTimestamp().toString();
        String bodyHtml = readFileAsString(EMAIL_TEMPLATE)
                .replace("PARAM_TIMESTAMP", timestamp);
        MimeMessage message = GmailHelper.createEmail(to, from, subject, "hello lethaiviet002@gmail.com", bodyHtml);
        GmailHelper gmailHelper1 = new GmailHelper();
        gmailHelper1.sendMessage(message);

        //test get mail and get link timestamp in it
        String query = String.format(QUERY_FORMAT, from, subject, timestamp);
        GmailHelper gmailHelper2 = new GmailHelper(query);
        String linkInEmail = gmailHelper2.executeQueryWithWaitingTime().getLinkInMsgWithQuery();
        System.out.println("linkInEmail: " + linkInEmail);
    }
}