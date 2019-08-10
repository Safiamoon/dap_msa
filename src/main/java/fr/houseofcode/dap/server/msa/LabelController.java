package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.google.GmailService;

/**
 * @author msa
 *
 */

@RestController
public class LabelController {
    @Autowired
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    private GmailService service;

    //TODO MSA by Djer |MVC| Tu aurais put ajouter cette méthode (et le MappinRequest) dans la classe "EmailController" les labels étant étroitement lié aux emails. De plus ils paratagent le même début d'URL.
    @RequestMapping("/Email/Label")
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    public String displaygetLabels(String userKey) throws IOException, GeneralSecurityException {
        return service.getLabels(userKey);
    }

}
