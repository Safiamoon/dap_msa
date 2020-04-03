package fr.houseofcode.dap.server.msa.test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Assert;
import org.junit.Test;

import fr.houseofcode.dap.server.msa.EmailController;
import fr.houseofcode.dap.server.msa.google.GmailServiceImpl;

//TODO MSA by Djer |Audit Code| Prend en compte les remarques de CheckStyle
//TODO MSA by Djer |JavaDoc| Commentaire JavaDoc manquant.
/**
 * @author sms
 *
 */
public class EmailControllerIT {

    @Test
    public void testDisplayNbUnreademail() throws IOException, GeneralSecurityException {
        //Init data
        EmailController ec = new EmailController();
        ec.setService(new GmailServiceImpl());

        //appel de la methode
        Integer result = ec.displayNbUnreademail("safia");

        //controle de l'execution de la methode
        Integer expectedNbEmail = 100;
        Assert.assertNotNull("Nombre d'email non présent", result);
        Assert.assertEquals(expectedNbEmail, result); //Integer.valueOf(100)
    }
}
