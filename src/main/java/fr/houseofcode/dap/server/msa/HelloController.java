package fr.houseofcode.dap.server.msa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author msa
 */
@RestController
//TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
public class HelloController {

    @RequestMapping("/")
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    public String index() {
        return "Welcome to Spring Boot !";
    }
}
