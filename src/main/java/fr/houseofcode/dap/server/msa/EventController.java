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
    private CalendarService service;

    @RequestMapping("/CalendarService/Event")
    public String displaynextEvent(@RequestParam String userKey) throws IOException, GeneralSecurityException {
        return service.nextEvent(userKey);
    }
}
