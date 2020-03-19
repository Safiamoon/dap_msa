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
	public static void main(final String[] args) throws IOException, GeneralSecurityException {
		LOG.debug("Début du main avec comme arguments : " + args);

	}

	/** * The internal application name. */
	private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
	/** * The default JsonFactory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

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
				DateTime end = event.getEnd().getDateTime();
				if (end == null) {
					end = event.getEnd().getDate();
				}

				result = result + " " + event.getSummary() + "(" + "the start of the event is: " + start
						+ "the end of the event " + end + ")\n";
			}
		}
		LOG.debug("The next event for " + userKey + "is : " + result);
		return result;
	}
}
