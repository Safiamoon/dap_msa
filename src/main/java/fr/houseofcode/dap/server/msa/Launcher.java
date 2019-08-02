package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.houseofcode.dap.server.msa.google.CalendarService;
import fr.houseofcode.dap.server.msa.google.GmailService;

/**
 * The main program entry Point.
 * @author adminHOC created a class
 */
public class Launcher {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * Launch GmailService and CalendarService at the same time but in different cases.
     * @param args is the external parameters
     * @throws IOException  if the sent or received  message's broken
     * @throws GeneralSecurityException in case there's a security failure	
     */
    public static void main(final String[] args) throws IOException, GeneralSecurityException {
        LOG.debug("Début du main avec comme arguments : " + args);

        Integer nbEmails;

        Integer choixUserEntier = Integer.parseInt(args[0]);

        GmailService gs = GmailService.getInstance();
        CalendarService cs = CalendarService.getInstance();

        String labels = gs.getLabels();
        nbEmails = gs.getNbUnreadEmail();
        String nextEvent;
        nextEvent = cs.nextEvent();

        switch (choixUserEntier) {

        case 1:
            LOG.debug("Connexion d'utilisateur dans ses labels : ");
            System.out.println(" Here are your Labels : " + "\n" + labels);

            break;

        case 2:
            LOG.debug("Connexion d'utilisateur dans sa boite mail ");
            System.out.println("Vous avez : " + nbEmails + " emails non lus");
            break;

        case 3:
            LOG.debug("Connexion d'utilisateur dans son GoogleCalendar : ");
            System.out.println("Your next event is : " + nextEvent);
            break;

        case 9:
            LOG.debug("Connexion d'utilisateur dans ses labels : ");
            LOG.debug("Connexion d'utilisateur dans sa boite mail: ");
            LOG.debug("Connexion d'utilisateur dans son GoogleCalendar : ");
            System.out.println(" Here are your Labels : " + "\n" + labels);

            System.out.println("Vous avez : " + nbEmails + " emails non lus");

            System.out.println("Your next event is : " + nextEvent);

            break;

        default:
            System.out.println("Pas d'option reconnue");

        }
    }

}
