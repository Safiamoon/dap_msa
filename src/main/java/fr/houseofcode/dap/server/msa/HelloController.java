package fr.houseofcode.dap.server.msa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO MSA by Djer |JavaDoc| Description de la classe  manquants.
/**
 *
 * @author msa
 */
@RestController
//TODO MSA by Djer |JavaDoc| Doublons, en général on rédige la JavaDoc AVANT les annotations (mais JAMAIS deux fois !)
/**
 * 
 * @author Safia
 *
 */
public class HelloController {
    //TODO MSA by Djer |IDE| Configure ton IDE pour que les indentations soient des espaces.
    /**
     *
     * @return welcoming message
     */
    @RequestMapping("/")
    public String index() {
        return "Welcome to Spring Boot !";
    }
}
