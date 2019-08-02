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
    private GmailService service;

    @RequestMapping("/Email/Label")
    public String displaygetLabels(String userKey) throws IOException, GeneralSecurityException {
        return service.getLabels(userKey);
    }

}
