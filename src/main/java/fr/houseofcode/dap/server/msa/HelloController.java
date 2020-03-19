package fr.houseofcode.dap.server.msa;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author msa
 */
@RestController
/**
 * 
 * @author Safia
 *
 */
public class HelloController {

	/**
	 * 
	 * @return welcoming message
	 */
	@RequestMapping("/")
	public String index() {
		return "Welcome to Spring Boot !";
	}
}
