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
 * 
 * @author adminHOC
 */
@Service
public class GmailServiceImpl implements GmailService {

    //TODO MSA by Djer |JavaDoc| "Logger." serait mieux. Cette constante est déja **très** connus. Deplus cette constante n'affiche rien du tout. Elle donne acces au logger et c'est Log4J qui affichera (en fonction de sa configuration définie dans le log4J.properties)
    /**
     * Display logs.
     */
    private static final Logger LOG = LogManager.getLogger();

    //TODO MSA by Djer |POO| Cette méthode n'est plus utiles.
    public static void main(final String[] args) throws IOException, GeneralSecurityException {
        LOG.debug("DÃ©but du main avec comme arguments : " + args);
    }

    /** The internal application name. */
    static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    /** The default JsonFactory. */
    static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Allow the secured access to Gmail.
     *
     * @param userKey
     * @return an instance GmailService with secured transport
     * @throws GeneralSecurityException in case there's a security failure
     * @throws IOException              if the sent or received message's broken
     */
    private Gmail getService(final String userKey) throws GeneralSecurityException, IOException {

        // Build a new authorized API client service.
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY, Utils.getCredentials(httpTransport, userKey))
                .setApplicationName(APPLICATION_NAME).build();
        LOG.debug("Allow the secured acces to " + userKey + "'s Gmail" + service);
        return service;
    }

    /**
     * Count the number of unread emails.
     *
     * @param userKey
     * @return the number of unread emails
     * @throws IOException              if the sent or received message's broken
     * @throws GeneralSecurityException in case there's a security failure
     */
    @Override
    public Integer getNbUnreadEmail(final String userKey) throws IOException, GeneralSecurityException {
        LOG.info("RÃ©cupÃ©ration du nbre d'Ã©mail pour l'utilisateur : " + userKey);

        String user = "me";

        // Alg to show number of unread emails
        ListMessagesResponse allMessages = getService(userKey).users().messages().list(user).setQ("is:unread in:inbox")
                .execute();
        List<Message> messages = allMessages.getMessages();

        //TODO MSA by Djer |POO| Evite les "blocs vides". Tu peux intervser la condition et ne pas mettre de "else" (ou éventuellement un "log.debug/info" dans le else)
        if (messages.isEmpty()) {
        } else {
            int number = messages.size();
            LOG.info("Number of unread messages : " + number);
            //TODO MSA by Djer |POO| Evite les multipels returns dans une même méthode. Créer uen variable de "retour" au début, puis affecte luis la bonne valeur au fir et à mesure du déroulement de ton algo.
            return number;

        }

        return null;
    }

    /**
     * Get all the labels from the Gmail account.
     *
     * @param userKey
     * @return the list of labels
     * @throws IOException              if the sent or received message's broken
     * @throws GeneralSecurityException in case there's a security failure
     */
    @Override
    public String getLabels(final String userKey) throws IOException, GeneralSecurityException {
        Gmail service2 = getService(userKey);
        String result = "";
        String user = "me";

        ListLabelsResponse labelsResponse = service2.users().labels().list(user).execute();
        List<Label> labels = labelsResponse.getLabels();
        if (labels.isEmpty()) {
            //TODO MSA by Djer |POO| Evite les multiples return dans une même méthodes. affecte la variable "result", tes if/eles existant évitent déja d'éxécuter du code inutile.
            return "No labels found.";
        } else {

            for (Label label : labels) {
                //TODO MSA by Djer |Rest API| Un service d'une API ne devrait pas s'occuper de l'affichage, c'est au client (ou la partie présentation (thymeleaf) de le faire). Icic renvoyer une "List<String>" serait plus approrié.
                result = result + "" + label.getName() + "\n";

            }
        }
        LOG.debug("Labels of Gmail for the user " + userKey + ":" + result);

        return result;
    }

    /**
     * List all Messages of the user's mailbox with labelIds applied.
     *
     * @param service  Authorized Gmail API instance.
     * @param userId   User's email address. The special value "me" can be used to
     *                 indicate the authenticated user.
     * @param labelIds Only return Messages with these labelIds applied.
     * @throws IOException in case there's a security failure
     * @return list of Messages with labels
     */
    public static List<Message> listMessagesWithLabels(final Gmail service, final String userId, List<String> labelIds)
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
        //TODO MSA by Djer |Log4J| Ton message devrait commencer par une majuscule (pout être cohérents avec les autres messages de logs)
        LOG.debug("list of emails and labels of the user " + userId + "'s gmail account :" + messages);
        return messages;
    }
}
