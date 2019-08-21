package fr.houseofcode.dap.server.msa.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 *
 * @author msa
 */
@Controller
//TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
public class GoogleAccount {
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc Manquant
    private static final int SENSIBLE_DATA_FIRST_CHAR = 0;
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc Manquant
    private static final int SENSIBLE_DATA_LAST_CHAR = 5;

    /**
     * Handle the Google response.
     * @param request The HTTP Request
     * @param code    The (encoded) code use by Google (token, expirationDate,...)
     * @param session the HTTP Session
     * @return the view to display
     * @throws ServletException When Google account could not be connected to DaP.
     * @throws GeneralSecurityException  //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc Manquant pour "GeneralSecurityException"
     */
    @RequestMapping("/oAuth2Callback")
    public String oAuthCallback(@RequestParam final String code, final HttpServletRequest request,
            final HttpSession session) throws ServletException, GeneralSecurityException {
        //TODO MSA by Djer |Log4J| Ne récupère pas un "Logger" dans chaque méthode. Un Logger n'a en général besoin d'être configuré qu'une fois par classe. Ici à chaque appel de la méthode, tu demandes à Log4J de te configurer un "Logger" et ca n'est pas utile. Le "Logger" devrait être un attribut de la classe.
        final Logger LOG = LogManager.getLogger();
        final String decodedCode = extracCode(request);

        final String redirectUri = buildRedirectUri(request, "/oAuth2Callback");

        final String userId = getUserid(session);
        try {
            final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            final GoogleAuthorizationCodeFlow flow = Utils.getFlow(httpTransport);
            final TokenResponse response = flow.newTokenRequest(decodedCode).setRedirectUri(redirectUri).execute();

            final Credential credential = flow.createAndStoreCredential(response, userId);
            if (null == credential || null == credential.getAccessToken()) {
                LOG.warn("Trying to store a NULL AccessToken for user : " + userId);
            }

            if (LOG.isDebugEnabled()) {
                if (null != credential && null != credential.getAccessToken()) {
                    LOG.debug("New user credential stored with userId : " + userId + "partial AccessToken : "
                            + credential.getAccessToken().substring(SENSIBLE_DATA_FIRST_CHAR, SENSIBLE_DATA_LAST_CHAR));
                }
            }
            // onSuccess(request, resp, credential);
        } catch (IOException e) {
            LOG.error("Exception while trying to store user Credential", e);
            throw new ServletException("Error while trying to connect Google Account");
        }

        return "redirect:/";
    }

    /**
     * retrieve the User ID in Session.
     * @param session the HTTP Session
     * @return the current User Id in Session
     * @throws ServletException if no User Id in session
     */

    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    private String getUserid(final HttpSession session) throws ServletException {
        //TODO MSA by Djer |Log4J| Logger en attribut et pas en variable local d'une méthode.
        final Logger LOG = LogManager.getLogger();
        String userId = null;
        if (null != session && null != session.getAttribute("userId")) {
            userId = (String) session.getAttribute("userId");
        }

        if (null == userId) {
            LOG.error("userId in Session is NULL in Callback");
            throw new ServletException("Error when trying to add Google acocunt : userId is NULL is User Session");
        }
        return userId;

    }

    /**
     * Add a Google account (user will be prompt to connect and accept required
     * access).
     * @param userId  the user to store Data //TODO MSA by Djer |Audit Code| (Checkstyle) balise @Param inutilisé pour "userId". En effet tu as renomé ce paramètre en "userKey" et du coups ta JavaDoc est devenue invalide.
     * @param request the HTTP request
     * @param session the HTTP session
     * @return //TODO MSA by Djer |Audit Code| (Checkstyle) Il manque la description du "return"
     * @throws GeneralSecurityException  //TODO MSA by Djer |Audit Code| (Checkstyle) Il manque la description du "GeneralSecurityException"
     */
    @RequestMapping("/account/add/{userId}")
    public String addAccount(@PathVariable("userId") final String userKey, final HttpServletRequest request,
            final HttpSession session) throws GeneralSecurityException {
        //TODO MSA by Djer |Log4J| Logger en attribut et pas en variable local d'une méthode.
        final Logger LOG = LogManager.getLogger();
        String response = "errorOccurs";
        GoogleAuthorizationCodeFlow flow;
        Credential credential = null;
        try {

            NetHttpTransport httpTansport = GoogleNetHttpTransport.newTrustedTransport();
            flow = Utils.getFlow(httpTansport);
            credential = flow.loadCredential(userKey);
            if (credential != null && credential.getAccessToken() != null) {
                response = "AccountAlreadyAdded";
            } else {
                // redirect to the authorization flow
                final AuthorizationCodeRequestUrl authorizationUrl = flow.newAuthorizationUrl();
                authorizationUrl.setRedirectUri(buildRedirectUri(request, "/oAuth2Callback"));
                // store userId in session for CallBack Access
                session.setAttribute("userId", userKey);
                response = "redirect:" + authorizationUrl.build();
            }
        } catch (IOException e) {
            LOG.error("Error while loading credential (or Google Flow)", e);
        }
        return response;

    }

    /**
     * Extract OAuth2 Google code (from URL) and decode it.
     * @param request the HTTP request to extract OAuth2 code
     * @return the decoded code
     * @throws ServletException if the code cannot be decoded
     */
    private String extracCode(final HttpServletRequest request) throws ServletException {
        //TODO MSA by Djer |Log4J| Logger en attribut et pas en variable local d'une méthode.
        final Logger LOG = LogManager.getLogger();
        final StringBuffer buf = request.getRequestURL();
        if (null != request.getQueryString()) {
            buf.append('?').append(request.getQueryString());
        }
        final AuthorizationCodeResponseUrl responseUrl = new AuthorizationCodeResponseUrl(buf.toString());
        final String decodeCode = responseUrl.getCode();

        if (decodeCode == null) {
            throw new MissingServletRequestParameterException("code", "String");
        }

        if (null != responseUrl.getError()) {
            LOG.error("Error when trying to add Google acocunt : " + responseUrl.getError());
            throw new ServletException("Error when trying to add Google acocunt");
            // onError(request, resp, responseUrl);
        }

        return decodeCode;
    }

    /**
     * Build a current host (and port) absolute URL.
     * @param req         The current HTTP request to extract schema, host, port
     *                    informations
     * @param destination the "path" to the resource
     * @return an absolute URI
     */
    protected String buildRedirectUri(final HttpServletRequest req, final String destination) {
        final GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath(destination);
        return url.build();
    }

}
