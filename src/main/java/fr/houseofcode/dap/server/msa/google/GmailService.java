package fr.houseofcode.dap.server.msa.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
 * Manage all Gmail data access.
 * @author adminHOC
 */
@Service
public final class GmailService {

    private static final Logger LOG = LogManager.getLogger();

    public static void main(final String[] args) throws IOException, GeneralSecurityException {
        LOG.debug("Début du main avec comme arguments : " + args);
    }

    /** The internal application name. */
    static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    /** The default JsonFactory. */
    static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** The default constructor. Private because is a singleton. */
    private GmailService() {

    }

    /**
     * Allow the secured access to Gmail.
     * @return an instance GmailService with secured transport
     * @throws GeneralSecurityException in case there's a security failure		
     * @throws IOException  if the sent or received  message's broken
     */
    private Gmail getService(String userKey) throws GeneralSecurityException, IOException {

        // Build a new authorized API client service.
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY, Utils.getCredentials(httpTransport, userKey))
                .setApplicationName(APPLICATION_NAME).build();
        LOG.debug("Allow the secured acces to Gmails : " + service);
        return service;
    }

    /**
     * Count the number of unread emails
     * @return the number of unread emails
     * @throws IOException if the sent or received  message's broken
     * @throws GeneralSecurityException in case there's a security failure
     */
    public Integer getNbUnreadEmail(String userKey) throws IOException, GeneralSecurityException {

        String user = "me";

        // Alg to show number of unread emails
        ListMessagesResponse allMessages = getService(userKey).users().messages().list(user).setQ("is:unread in:inbox")
                .execute();
        List<Message> messages = allMessages.getMessages();
        if (messages.isEmpty()) {

        } else {
            int number = messages.size();
            LOG.debug("Number of unread messages : " + number);
            return number;

        }

        return null;
    }

    /**
     * Get all the labels from the Gmail account.
     * @return the list of labels
     * @throws IOException if the sent or received  message's broken
     * @throws GeneralSecurityException in case there's a security failure
     */
    public String getLabels(String userKey) throws IOException, GeneralSecurityException {
        Gmail service2 = getService(userKey);
        String result = " ";
        String user = "me";

        ListLabelsResponse labelsResponse = service2.users().labels().list(user).execute();
        List<Label> labels = labelsResponse.getLabels();
        if (labels.isEmpty()) {

            return "No labels found.";
        } else {

            for (Label label : labels) {
                result = result + " " + label.getName() + "\n";

            }
        }
        LOG.debug("Labels of Gmail : " + result);

        return result;
    }

    /**
      * List all Messages of the user's mailbox with labelIds applied.
      *
      * @param service Authorized Gmail API instance.
      * @param userId User's email address. The special value "me"
      * can be used to indicate the authenticated user.
      * @param labelIds Only return Messages with these labelIds applied.
      * @throws IOException in case there's a security failure
      */
    public static List<Message> listMessagesWithLabels(Gmail service, String userId, List<String> labelIds)
            throws IOException {
        ListMessagesResponse response = service.users().messages().list(userId).setLabelIds(labelIds).execute();

        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = service.users().messages().list(userId).setLabelIds(labelIds).setPageToken(pageToken)
                        .execute();
            } else {
                break;
            }
        }
        LOG.debug("Labels and list emails of Gmail : " + messages);
        return messages;
    }
}
