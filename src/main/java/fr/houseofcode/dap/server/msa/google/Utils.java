package fr.houseofcode.dap.server.msa.google;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
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
 * 
 * @author adminHOC created a class and named it Utils
 */
public class Utils {
	/**
	 * the Json default factory.
	 */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/**
	 * Tokens directory path
	 */
	private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "\\Dap\\tokens"
			+ File.separator;

	/**
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = new ArrayList<String>();

	/**
	 * Credentials file path.
	 */
	private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + "\\Dap\\credentials.json"
			+ File.separator;

	/**
	 * Creates an authorized Credential object.
	 *
	 * @param HTTP_TRANSPORT
	 * @return An authorized Credential object.
	 * @throws IOException if the sent or received message's broken
	 */
	public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, String userKey) throws IOException {
		// System.getProperty("user.home");

		// LocalServerReceiver receiver = new
		// LocalServerReceiver.Builder().setPort(8888).build();
		// return new AuthorizationCodeInstalledApp(getFlow(HTTP_TRANSPORT),
		// receiver).authorize("user");

		GoogleAuthorizationCodeFlow flow = getFlow(HTTP_TRANSPORT);
		return flow.loadCredential(userKey);

	}

	/**
	 *
	 * @param httpTransport
	 * @return flow
	 * @throws IOException
	 */
	static GoogleAuthorizationCodeFlow getFlow(final NetHttpTransport httpTransport) throws IOException {

		SCOPES.add(CalendarScopes.CALENDAR_READONLY);
		SCOPES.add(GmailScopes.GMAIL_READONLY);
		File clientSecretsFic = new File(CREDENTIALS_FILE_PATH);
		if (!clientSecretsFic.exists()) {
			throw new FileNotFoundException();
		}
		FileInputStream in = new FileInputStream(clientSecretsFic);
		Reader read = new InputStreamReader(in, StandardCharsets.UTF_8);
		GoogleClientSecrets clSecrets = GoogleClientSecrets.load(JSON_FACTORY, read);

		java.io.File dataDirectory = new java.io.File(TOKENS_DIRECTORY_PATH);
		FileDataStoreFactory dataStore = new FileDataStoreFactory(dataDirectory);
		return new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clSecrets, SCOPES)
				.setDataStoreFactory(dataStore).setAccessType("online").build();

	}
}
