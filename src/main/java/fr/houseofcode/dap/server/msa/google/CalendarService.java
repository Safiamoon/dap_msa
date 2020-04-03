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
 *
 * @author admin HOC.
 */
@Service
public final class CalendarService {
    /**
     * Display log.
     */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Log message.
     *
     * @param args
     * @throws IOException
     * @throws GeneralSecurityException
     */
    //TODO MSA by Djer |POO| Cette méthode n'est plus utiles.
    public static void main(final String[] args) throws IOException, GeneralSecurityException {
        //TODO MSA by Djer |IDE| Ce fichier a étté créé par "admin HOC" et tu ne l'as pas modifié, il est donc en UTF-8 (ce qui est valides) mais d'autres fichiers sources sont en "ISO-8859-1" ....
        LOG.debug("DÃ©but du main avec comme arguments : " + args);

    }

    /** * The internal application name. */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /** * The default JsonFactory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    //TODO MSA by Djer |JavaDoc| Commentaires Javadoc manquants (paramètre)
    /**
     * Get the next event.
     *
     * @param userKey
     * @throws IOException              if the sent or received message's broken
     * @throws GeneralSecurityException in case there's a security failure
     * @return Textual representation of the next Event
     */
    public String nextEvent(final String userKey) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY,
                Utils.getCredentials(httpTransport, userKey)).setApplicationName(APPLICATION_NAME).build();

        String result = "";
        //TODO MSA by Djer |POO| Commentaires faux, ne récupère que **1** évènnement.
        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary").setMaxResults(1).setTimeMin(now).setOrderBy("startTime")
                .setSingleEvents(true).execute();
        List<Event> items = events.getItems();
        // return items
        if (items.isEmpty()) {
            //TODO MSA by Djer |POO| Evite les multiples return dans une même méthodes. affecte la variable "result", tes if/eles existant évitent déja d'éxécuter du code inutile.
            return "No upcoming events found.";
        } else {

            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                DateTime end = event.getEnd().getDateTime();
                if (end == null) {
                    end = event.getEnd().getDate();
                }

                //TODO MSA by Djer |Rest API| Un service d'une API ne devrait pas s'occuper de l'affichage, c'est au client (ou la partie présentation (thymeleaf) de le faire).
                result = result + " " + event.getSummary() + "(" + "the start of the event is: " + start
                        + "the end of the event " + end + ")\n";
            }
        }
        LOG.debug("The next event for " + userKey + "is : " + result);
        return result;
    }
}
