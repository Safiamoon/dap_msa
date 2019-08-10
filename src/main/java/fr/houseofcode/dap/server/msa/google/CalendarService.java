package fr.houseofcode.dap.server.msa.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

/**
 * Manage all Calendar data access.
 * @author admin HOC.
 */
@Service
public class CalendarService {
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    private static final Logger LOG = LogManager.getLogger();

    //TODO MSA by Djer |Command Line| La méthode "main" n'est utile que comme point d'entré du programme, elle n'est pas (plus) utile dans cette classe.
    public static void main(final String[] args) throws IOException, GeneralSecurityException {
        LOG.debug("Début du main avec comme arguments : " + args);

    }

    /** *  The internal application name. */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /** * The default JsonFactory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /** Singleton. */
    //TODO MSA by Djer |Design Patern| Nous n'avons plus besoin de gérer le Singleton SpringFramework le fait pour nous.
    private static CalendarService instance;

    /* Is an empty private constructor (cf.Singleton pattern). */
    //TODO MSA by Djer |Design Patern| Ce constructeur "privé" n'est plus utile, c'est SpringFramework qui gère le Singleton pour nous maintenant.
    private CalendarService() {

    }

    /**
     *  Description Singleton.
     * @return
     */
    //TODO MSA by Djer |Design Patern| Nous n'avons plus besoin de gérer le Singleton SpringFramework le fait pour nous.
    public static CalendarService getInstance() {
        if (instance == null) {
            instance = new CalendarService();
        }
        return instance;
    }

    /**
     * 	Get the next event.
     * @return Textual representation of the next Event
     * @throws IOException if the sent or received  message's broken
     * @throws GeneralSecurityException in case there's a security failure  
     */
    //TODO MSA by Djer |Audit Code| (Checkstyle) le paramètre "userKey" devrait être final. En effet le corps de cette méthode ne va pas modifié (le pointeur) de ce paramètre. C'est une bonne habitude de ne PAS modifié le pointeur d'un paramètre. La quais totalité des paramètres sont donc "final"
    public String nextEvent(String userKey) throws IOException, GeneralSecurityException {

        LOG.info("Start of the get next event method");
        // Build a new authorized API client service.
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY,
                Utils.getCredentials(httpTransport, userKey)).setApplicationName(APPLICATION_NAME).build();

        String result = "";
        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary").setMaxResults(1).setTimeMin(now).setOrderBy("startTime")
                .setSingleEvents(true).execute();
        List<Event> items = events.getItems();
        // return items
        if (items.isEmpty()) {
            return "No upcoming events found.";
        } else {

            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                result = result + " " + event.getSummary() + "(" + start + ")\n";
                //	System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
        LOG.debug("The next event is : " + result);
        return result;
    }
}
