package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.google.CalendarService;

/**
 * @author msa
 *
 */
@RestController
public class EventController {
    @Autowired
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    private CalendarService service;

    //TODO MSA by Djer |MVC| "/Calendar" (ou "/Event") serait mieux. Il faut éviter de préciser ce genre de détails "interne" à l'appelant.
    @RequestMapping("/CalendarService/Event")
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    public String displaynextEvent(@RequestParam String userKey) throws IOException, GeneralSecurityException {
        return service.nextEvent(userKey);
    }
}
