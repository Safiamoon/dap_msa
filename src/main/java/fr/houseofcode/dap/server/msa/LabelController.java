package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.google.GmailServiceImpl;

//TODO MSA by Djer |Audit Code| Prends en compte les rmarques de CheckStyle.
//TODO MSA by Djer |JavaDoc| Description de la classe  manquants.
/**
 *
 * @author msa
 */
@RestController
public class LabelController {
    //TODO MSA by Djer |IDE| Configure ton IDE pour que les indentations soient des espaces.
    /**
     * Gmail service implementation.
     */
    @Autowired
    private GmailServiceImpl service;

    //TODO MSA by Djer |JavaDoc| Commentaires Javadoc manquants (paramètre, Exceptions et valeur de retour)
    /**
     * Display email labels.
     *
     * @param userKey
     * @throws IOException
     * @throws GeneralSecurityException
     * @return email labels
     */
    @RequestMapping("/Email/Label")
    public String displaygetLabels(final String userKey) throws IOException, GeneralSecurityException {
        return service.getLabels(userKey);
    }

}
