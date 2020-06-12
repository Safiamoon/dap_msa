package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.google.CalendarService;

//TODO MSA by Djer |Audit Code| Prends en compte les rmarques de CheckStyle.
//TODO MSA by Djer |JavaDoc| Description de la classe manquante.
/**
 *
 * @author msa
 */
@RestController
public class EventController {
    //TODO MSA by Djer |IDE| Configure ton IDE pour que les indentations soient des espaces.
    /**
     * CalendarService API.
     */
    @Autowired
    private CalendarService service;

    //TODO MSA by Djer |JavaDoc| Commentaires Javadoc manquants (paramètre, Exceptions et valeur de retour)
    /**
     *
     * display next event.
     *
     * @param userKey
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     * @return next event
     */
    @RequestMapping("/event/next")
    public final String displaynextEvent(@RequestParam final String userKey)
            throws IOException, GeneralSecurityException {
        return service.nextEvent(userKey);
    }
}
