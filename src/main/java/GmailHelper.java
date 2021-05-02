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
import lombok.ToString;
import org.apache.commons.lang3.time.StopWatch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class GmailHelper {
    private static final String APPLICATION_NAME = "Gmail-API-demo";
    private static final String TOKENS_DIRECTORY_PATH = "src/main/resources/tokens";
    private static final String CREDENTIALS_FILE_PATH = TOKENS_DIRECTORY_PATH + "/client_secret.json";
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final Gmail gmailService;
    private static final long MESSAGE_LIST_MAX_RESULTS = 5L;
    private String query;
    private List<Message> messages;

//    @SneakyThrows
    public GmailHelper(String query) throws GeneralSecurityException, IOException {
        this();
        this.query = query;
    }

//    @SneakyThrows
    public GmailHelper() throws GeneralSecurityException, IOException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        this.gmailService = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

//    @SneakyThrows
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
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

//    @SneakyThrows
    public GmailHelper executeQueryWithWaitingTime() throws InterruptedException, IOException, MessagingException {
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

//    @SneakyThrows
    public void executeQuery() throws IOException, MessagingException {
        String gmailUser = "me";
        Gmail.Users.Messages.List list = this.gmailService.users().messages().list(gmailUser).
                setMaxResults(MESSAGE_LIST_MAX_RESULTS).setQ(this.query);
        this.messages = list.execute().getMessages();

        String a = getMimeMessage(this.messages.get(0).getId());

        Document doc = Jsoup.parse(a);

        String url = doc.getElementsByAttribute("href").attr("href");
    }

    /**
     * Returns "fully-qualified" message.
     *
     * @param id identifier of the message to be obtained.
     * @return
     */
    public Message getFullyQualifiedMessage(String id) throws IOException {
        return gmailService.users().messages().get("me", id)
                .setFormat("RAW")
                .execute();
    }

    public boolean doMessagesExist() {
        return this.messages != null;
    }

    public int countMessages() {
        return this.messages == null ? 0 : this.messages.size();
    }

//    @SneakyThrows
    public String getMimeMessage(String messageId) throws IOException, MessagingException {
        Message message = getFullyQualifiedMessage(messageId);
        String messageStr = message.getRaw()
                .replace('-', '+')
                .replace('_', '/');
        byte[] emailBytes = Base64.getDecoder().decode(messageStr);
        Session session = Session.getDefaultInstance(new Properties(), null);
        MimeMessage email = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
        return getText(email);
    }

    //https://stackoverflow.com/questions/24428246/retrieve-email-message-body-in-html-using-gmail-api
//    @SneakyThrows
    public static String getText(Part p) throws IOException, MessagingException {
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

    @SneakyThrows
    public static void clientLevel() {
        libraryLevel();
    }

    public static void libraryLevel() throws IOException {
        throw new IOException("**evil laugh**");
    }
}
