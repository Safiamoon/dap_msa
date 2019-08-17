package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.google.GmailService;

/**
 * @author msa
 *
 */
@RestController
public class EmailController {
    @Autowired
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    private GmailService service;

    @RequestMapping("/Email/Unread")
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    public Integer displayNbUnreademail(@RequestParam String userKey) throws IOException, GeneralSecurityException {
        return service.getNbUnreadEmail(userKey);
    }
}
