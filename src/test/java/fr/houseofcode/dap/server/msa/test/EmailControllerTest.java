/**
 * 
 */
package fr.houseofcode.dap.server.msa.test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Test;

import fr.houseofcode.dap.server.msa.EmailController;

/**
 * @author sms
 *
 */
public class EmailControllerTest {

    @Test
    public void testDisplayNbUnreademail() throws IOException, GeneralSecurityException {
        //Init data
        EmailController ec = new EmailController();
        ec.setService(new GmailServiceMock());
        //appel de la methode
        ec.displayNbUnreademail("safia");

        //controle de l'execution de la methode

    }
}
