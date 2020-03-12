package fr.houseofcode.dap.server.msa.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GmailService {
    /**
     * Renvoie le nombre d'email non lus dans la boite principale
     * @param userKey
     * @return le nombre
     * @throws IOException technical error
     * @throws GeneralSecurityException Google authorization error
     */
    Integer getNbUnreadEmail(String userKey) throws IOException, GeneralSecurityException;

    String getLabels(String userKey) throws IOException, GeneralSecurityException;

}
