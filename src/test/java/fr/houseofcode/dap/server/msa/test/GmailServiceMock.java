/**
 * 
 */
package fr.houseofcode.dap.server.msa.test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import fr.houseofcode.dap.server.msa.google.GmailService;

/**
 * @author sms
 *
 */
public class GmailServiceMock implements GmailService {
    /**
     * Mock : renvoie le nombre d'email non lus 
     * Count the number of unread emails //TODO MSA by Djer |Audit Code| (Checkstyle) La première ligne d'une Javadoc doit finir par un . (point). En effet c'est une phrase (courte) qui décrit l'élément (ici ta méthode) et kes phrases se terminent par un . (point)
     * @return the number of unread emails
     * @throws IOException if the sent or received  message's broken
     * @throws GeneralSecurityException in case there's a security failure
     */
    public Integer getNbUnreadEmail(String userKey) throws IOException, GeneralSecurityException {

        return 100;

    }

    @Override
    public String getLabels(String userKey) throws IOException, GeneralSecurityException {

        return "bouchon, list, label";
    }

}
