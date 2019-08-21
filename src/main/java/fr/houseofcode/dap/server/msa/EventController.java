package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.google.CalendarService;

/**
 *
 * @author msa
 */
@RestController
//TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
public class EventController {
    @Autowired
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    private CalendarService service;

    //TODO MSA by Djer |MVC| "/calendar/next" (ou "/event/next") serait mieux. Il faut éviter de préciser ce genre de détails "interne" à l'appelant.
    @RequestMapping("/CalendarService/Event")
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    //TODO MSA by Djer |Audit Code| (Checkstyle) le paramètre userKey pourrait être déclaré final
    public String displaynextEvent(@RequestParam String userKey) throws IOException, GeneralSecurityException {
        return service.nextEvent(userKey);
    }
}
