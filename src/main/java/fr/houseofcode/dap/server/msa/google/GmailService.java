package fr.houseofcode.dap.server.msa.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

//TODO MSA by Djer |JavaDoc| Commentaire JavaDoc manquant.

public interface GmailService {
    //TODO MSA by Djer |JavaDoc| Commentaires Javadoc manquants (paramètre)
    /**
     * Display the number of unread emails in the inbox.
     *
     * @param userKey
     * @return the number of unread emails
     * @throws IOException              technical error
     * @throws GeneralSecurityException Google authorization error
     */
    Integer getNbUnreadEmail(String userKey) throws IOException, GeneralSecurityException;

    //TODO MSA by Djer |JavaDoc| Commentaire JavaDoc manquant.
    String getLabels(String userKey) throws IOException, GeneralSecurityException;

}
