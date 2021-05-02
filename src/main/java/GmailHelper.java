import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.time.StopWatch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.security.GeneralSecurityException;
//import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class GmailHelper {
    private static final String APPLICATION_NAME = "Gmail-API-demo";
    private static final String TOKENS_DIRECTORY_PATH = "src/main/resources/tokens";
    private static final String CREDENTIALS_FILE_PATH = TOKENS_DIRECTORY_PATH + "/client_secret.json";
    private static final String GMAIL_USER = "me";
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.MAIL_GOOGLE_COM);
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final long MESSAGE_LIST_MAX_RESULTS = 5L;

    private final Gmail gmailService;
    private String query;
    private List<Message> messages;

    @SneakyThrows
    public GmailHelper(String query) {
        this();
        this.query = query;
    }

    @SneakyThrows
    public GmailHelper() {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        this.gmailService = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    @SneakyThrows
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) {
        // Load client secrets.
        InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .setApprovalPrompt("force")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(1239).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Create a MimeMessage using the parameters provided.
     *
     * @param to       email address of the receiver
     * @param from     email address of the sender, the mailbox account
     * @param subject  subject of the email
     * @param bodyText body text of the email
     * @return the MimeMessage to be used to send email
     * @throws MessagingException
     */
    @SneakyThrows
    public static MimeMessage createEmail(String to,
                                          String from,
                                          String subject,
                                          String bodyText,
                                          String bodyHtml) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);


        if (bodyHtml != null) {
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(bodyText, "utf-8");

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(bodyHtml, "text/html; charset=utf-8");

            Multipart multiPart = new MimeMultipart("alternative");
            multiPart.addBodyPart(textPart);
            multiPart.addBodyPart(htmlPart);
            email.setContent(multiPart);
        } else {
            email.setText(bodyText);
        }

        return email;
    }

    @SneakyThrows
    public static MimeMessage createEmail(String to,
                                          String from,
                                          String subject,
                                          String bodyText) {
        return createEmail(to, from, subject, bodyText, null);
    }

    /**
     * Create a message from an email.
     *
     * @param emailContent Email to be set to raw of message
     * @return a message containing a base64url encoded email
     * @throws IOException
     * @throws MessagingException
     */
    @SneakyThrows
    public static Message createMessageWithEmail(MimeMessage emailContent) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

    /**
     * Create a MimeMessage using the parameters provided.
     *
     * @param to       Email address of the receiver.
     * @param from     Email address of the sender, the mailbox account.
     * @param subject  Subject of the email.
     * @param bodyText Body text of the email.
     * @param file     Path to the file to be attached.
     * @return MimeMessage to be used to send email.
     * @throws MessagingException
     */
    @SneakyThrows
    public static MimeMessage createEmailWithAttachment(String to,
                                                        String from,
                                                        String subject,
                                                        String bodyText,
                                                        File file) {
        Session session = Session.getDefaultInstance(new Properties(), null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(bodyText, "text/plain");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        mimeBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(file);

        mimeBodyPart.setDataHandler(new DataHandler(source));
        mimeBodyPart.setFileName(file.getName());

        multipart.addBodyPart(mimeBodyPart);
        email.setContent(multipart);

        return email;
    }

    /**
     * Send an email from the user's mailbox to its recipient.
     *
     * @param service      Authorized Gmail API instance.
     * @param userId       User's email address. The special value "me"
     *                     can be used to indicate the authenticated user.
     * @param emailContent Email to be sent.
     * @return The sent message
     * @throws MessagingException
     * @throws IOException
     */
    @SneakyThrows
    public Message sendMessage(MimeMessage emailContent) {
        Message message = createMessageWithEmail(emailContent);
        message = this.gmailService.users().messages().send(GMAIL_USER, message).execute();

        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
        return message;
    }

    @SneakyThrows
    public GmailHelper executeQueryWithWaitingTime() {
        StopWatch sw = new StopWatch();
        sw.start();
        int timeInMilliseconds = 180 * 1000;
        while (sw.getTime() < timeInMilliseconds) {
            this.executeQuery();
            if (this.messages != null) break;

            Thread.sleep(3 * 1000);
        }
        sw.stop();

        return this;
    }

    @SneakyThrows
    public void executeQuery() {
        Gmail.Users.Messages.List list = this.gmailService.users().messages()
                .list(GMAIL_USER)
                .setMaxResults(MESSAGE_LIST_MAX_RESULTS)
                .setQ(this.query);
        this.messages = list.execute().getMessages();
    }

    /**
     * Returns "fully-qualified" message.
     *
     * @param id identifier of the message to be obtained.
     * @return
     */
    @SneakyThrows
    public Message getFullyQualifiedMessage(String id) {
        return gmailService.users().messages()
                .get("me", id)
                .setFormat("RAW")
                .execute();
    }

    public boolean hasMessagesInbox() {
        return this.messages != null;
    }

    public int getNumberOfMsgWithQuery() {
        return this.messages == null ? 0 : this.messages.size();
    }

    public String getLinkInMsgWithQuery() {
        if (!hasMessagesInbox()) return null;
        String id = this.messages.get(0).getId();
        String msgHtml = getMimeMessage(id);
        Document doc = Jsoup.parse(msgHtml);
        return doc.select("a").attr("href");
    }

    @SneakyThrows
    public String getMimeMessage(String messageId) {
        Message message = getFullyQualifiedMessage(messageId);
        byte[] emailBytes = Base64.decodeBase64(message.getRaw());
        Session session = Session.getDefaultInstance(new Properties(), null);
        MimeMessage email = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
        return getText(email);
    }

    //https://stackoverflow.com/questions/24428246/retrieve-email-message-body-in-html-using-gmail-api
    @SneakyThrows
    public static String getText(Part p) {
        if (p.isMimeType("text/*")) return (String) p.getContent();

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart) p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null) text = getText(bp);
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null) return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null) return s;
            }
        }
        return null;
    }
}
