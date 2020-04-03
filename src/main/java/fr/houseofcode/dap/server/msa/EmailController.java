package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.google.GmailService;

//TODO MSA by Djer |Audit Code| Description de la classe manquante.
/**
 * @author msa
 *
 */
@RestController
public class EmailController {

    //TODO MSA by Djer |IDE| Configure ton IDE pour que les indentations soient des espaces, et non des tabulation (Windows->Preferences->Java->Code Style->Formatter). Tu ne veras plus ces warning, car je te formate les classes sur lesquelles je fait des modifications.
    //TODO MSA by Djer |JavaDoc| On met en générale ces commentaires JavaDoc sur une seul ligne (je te l'ai modifié pour l'exemple)
    /** Display logs. */
    private static final Logger LOG = LogManager.getLogger();

    /**
     * GmailService API.
     */
    @Autowired
    private GmailService service;

    //TODO MSA by Djer |JavaDoc| Commentaires Javadoc manquants (paramètre, Exceptions et valeur de retour)
    /**
     * Display number of unread emails.
     *
     * @param userKey
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     * @return number of unread emails
     */
    //TODO MSA by Djer |Rest API| Devrait commencer par une minuscule car toutes tes autres routes (requetsMapping) commencent par une minuscules.
    @RequestMapping("/Email/Unread")
    public final Integer displayNbUnreademail(@RequestParam final String userKey)
            throws IOException, GeneralSecurityException {
        LOG.info("Affichage de nombre d'email pour l'utilisateur :" + userKey);
        return service.getNbUnreadEmail(userKey);
    }

    /**
     * Define the Gmail Service
     *
     * @param gmailService the (mock?) service
     */
    public void setService(final GmailService gmailService) {
        this.service = gmailService;

    }
}
