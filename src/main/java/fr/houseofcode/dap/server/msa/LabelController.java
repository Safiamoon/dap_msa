package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.google.GmailServiceImpl;

/**
 *
 * @author msa
 */
@RestController
public class LabelController {

	/**
	 * Gmail service implementation.
	 */
	@Autowired
	private GmailServiceImpl service;

	/**
	 * Display email labels.
	 *
	 * @param userKey
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @return email labels
	 */
	@RequestMapping("/Email/Label")
	public String displaygetLabels(final String userKey) throws IOException, GeneralSecurityException {
		return service.getLabels(userKey);
	}

}
