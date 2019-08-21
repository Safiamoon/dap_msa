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

    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    private static final Logger LOG = LogManager.getLogger();

    //TODO MSA by Djer |Command Line| La méthode "main" n'est utile que comme point d'entré du programme, elle n'est pas (plus) utile dans cette classe.
    public static void main(final String[] args) throws IOException, GeneralSecurityException {
        LOG.debug("Début du main avec comme arguments : " + args);
    }

    /** The internal application name. */
    static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    /** The default JsonFactory. */
    static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    //TODO MSA by Djer |Design Patern| Ce constructeur était privé lorsque l'on gérait le "Singleton" nous-même, ca n'est plus utile (Spring fait lui même un Singleton). Tu peux supprimer ce constructeur, celui par defaut généré par Java fonctionenra bien ici.
    /** The default constructor. Private because is a singleton. */
    private GmailService() {

    }

    /**
     * Allow the secured access to Gmail.
     * @return an instance GmailService with secured transport
     * @throws GeneralSecurityException in case there's a security failure		 //TODO MSA by Djer |Audit Code| (Checkstyle) Evite les espaces vides en fin de ligne
     * @throws IOException  if the sent or received  message's broken
     */
    //TODO MSA by Djer |Audit Code| (Checkstyle) le paramètre "userKey" devrait être final. En effet le corps de cette méthode ne va pas modifié (le pointeur) de ce paramètre. C'est une bonne habitude de ne PAS modifié le pointeur d'un paramètre. La quais totalité des paramètres sont donc "final"
    private Gmail getService(String userKey) throws GeneralSecurityException, IOException {

        // Build a new authorized API client service.
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY, Utils.getCredentials(httpTransport, userKey))
                .setApplicationName(APPLICATION_NAME).build();
        //TODO MSA by Djer |Log4J| Contextualise tes messages de log (ajoute "for userKey : " + userKey) 
        LOG.debug("Allow the secured acces to Gmails : " + service);
        return service;
    }

    /**
     * Count the number of unread emails //TODO MSA by Djer |Audit Code| (Checkstyle) La première ligne d'une Javadoc doit finir par un . (point). En effet c'est une phrase (courte) qui décrit l'élément (ici ta méthode) et kes phrases se terminent par un . (point)
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
            //TODO MSA by Djer |Audit Code| (Checkstyle) Le bloc devrait contenir au moins une instruction. Tu peux mettre une LOG de debug/info. Ou alors inverser ta condition dans le "if" et transféré le bloc "else" dans ce block
        } else {
            int number = messages.size();
            LOG.debug("Number of unread messages : " + number);
            //TODO MSA by Djer |POO| Evite d'avoir plusieurs return dans une méthode. Initialise une varaible au début de la méthode, valorise cette variable en fonction des règles métier puis renvoie la valeur de cette variable.
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
    //TODO MSA by Djer |Audit Code| (Checkstyle) La balise javadoc @param est manquante pour "userKey"
    public String getLabels(String userKey) throws IOException, GeneralSecurityException {
        Gmail service2 = getService(userKey);
        //TODO MSA by Djer |POO| Evite de mettre un espace, il vaut mieu initialiser avec "vide" (sans espaces). Ici ton premier label aura un espace en plus au début
        String result = " ";
        String user = "me";

        ListLabelsResponse labelsResponse = service2.users().labels().list(user).execute();
        List<Label> labels = labelsResponse.getLabels();
        if (labels.isEmpty()) {
            //TODO MSA by Djer |POO| Evite d'avoir plusieurs return dans une méthode. Initialise une varaible au début de la méthode, valorise cette variable en fonction des règles métier puis renvoie la valeur de cette variable. En plus ici tu as déja "result" qui est prete pour cela.
            return "No labels found.";
        } else {

            for (Label label : labels) {
                result = result + " " + label.getName() + "\n";

            }
        }
        //TODO MSA by Djer |Log4J| Contextualise tes messages de log (ajoute "for userKey : " + userKey) 
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
        //TODO MSA by Djer |JavaDoc| L'utilisation de "setLabelIds(labelIds)" (ligne 133) permet d'appliquer un "filtre" (uniquement les messages qui ont ce "label"). La valeur de "labelsIds" devrait apparaître dans ton message de log pour mieux contextualiser la liste des messages déja affiché
        //TODO MSA by Djer |Log4J| Contextualise tes messages de log (ajoute "for userKey : " + userKey) 
        LOG.debug("Labels and list emails of Gmail : " + messages);
        return messages;
    }
}
