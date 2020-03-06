package fr.houseofcode.dap.server.msa.google;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;

/**
 * Instance of scopes for Gmail and Calendar
 * @author adminHOC created a class and named it Utils
 */
public class Utils {
    /** * The default JsonFactory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** */
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "\\dap\\tokens";
  

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = new ArrayList<String>();
    private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + "\\dap\\credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param httpTransport The network HTTP Transport. //TODO MSA by Djer |Audit Code| (Checkstyle) balise @Param inutilisé pour "httpTransport". Elles ne sont pas ecrit pareile, La version CamelCase est la façon la plus correct de l'écrire.
     * @return An authorized Credential object.
     * @throws IOException if the sent or received  message's broken
     */
    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, String userKey) throws IOException {
        // System.getProperty("user.home");

        //LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //return new AuthorizationCodeInstalledApp(getFlow(HTTP_TRANSPORT), receiver).authorize("user");

        GoogleAuthorizationCodeFlow flow = getFlow(HTTP_TRANSPORT);
        return flow.loadCredential(userKey);

    }

    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    static GoogleAuthorizationCodeFlow getFlow(final NetHttpTransport httpTransport) throws IOException {

        //TODO MSA by Djer |IDE| Supprime les TO-DO créé automatiquement par Eclipse une fois que tu les as traité
        // TODO Auto-generated method stub
        // Build flow and trigger     user authorization request.
        SCOPES.add(CalendarScopes.CALENDAR_READONLY);
        SCOPES.add(GmailScopes.GMAIL_READONLY);
        //InputStream in = CalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);

        //        if (in == null) {
        //            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        //        }

        File clientSecretsFic = new File(CREDENTIALS_FILE_PATH);
        Reader read = new InputStreamReader(new FileInputStream(clientSecretsFic), Charset.forName("UTF-8"));

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, read);
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, SCOPES)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                        .setAccessType("offline").build();
        return flow;

    }
}
