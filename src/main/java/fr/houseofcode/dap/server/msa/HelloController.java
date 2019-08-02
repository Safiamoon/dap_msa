package fr.houseofcode.dap.server.msa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author msa
 *
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Welcome to Spring Boot !";
    }
}
