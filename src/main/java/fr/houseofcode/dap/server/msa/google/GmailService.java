package fr.houseofcode.dap.server.msa.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GmailService {
	/**
	 * Display the number of unread emails in the inbox.
	 *
	 * @param userKey
	 * @return the number of unread emails
	 * @throws IOException              technical error
	 * @throws GeneralSecurityException Google authorization error
	 */
	Integer getNbUnreadEmail(String userKey) throws IOException, GeneralSecurityException;

	String getLabels(String userKey) throws IOException, GeneralSecurityException;

}
