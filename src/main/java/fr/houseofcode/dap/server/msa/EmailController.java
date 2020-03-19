package fr.houseofcode.dap.server.msa;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.google.GmailService;

/**
 * @author msa
 *
 */
@RestController
public class EmailController {

	/**
	 * Display logs.
	 */
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * GmailService API.
	 */
	@Autowired
	private GmailService service;

	/**
	 * Display number of unread emails.
	 *
	 * @param userKey
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @return number of unread emails
	 */
	@RequestMapping("/Email/Unread")
	public final Integer displayNbUnreademail(@RequestParam final String userKey)
			throws IOException, GeneralSecurityException {
		LOG.info("Affichage de nombre d'email pour l'utilisateur :" + userKey);
		return service.getNbUnreadEmail(userKey);
	}

	/**
	 * Define the Gmail Service
	 *
	 * @param gmailService the (mock?) service
	 */
	public void setService(final GmailService gmailService) {
		this.service = gmailService;

	}
}
