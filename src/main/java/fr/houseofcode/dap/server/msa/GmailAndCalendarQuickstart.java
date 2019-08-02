package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import fr.houseofcode.dap.server.msa.google.CalendarService;
import fr.houseofcode.dap.server.msa.google.GmailService;

public class GmailAndCalendarQuickstart {

	public static void main(String... args) throws IOException, GeneralSecurityException {

		//Afficher Nb Email
		GmailService gs = GmailService.getInstance();
		gs.getNbUnreadEmail();
		//Afficher Prochain Evnet
		CalendarService cs = CalendarService.getInstance();
		cs.nextEvent();

	}

}
