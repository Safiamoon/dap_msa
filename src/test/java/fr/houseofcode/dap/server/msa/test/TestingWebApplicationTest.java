/**
 * 
 */
package fr.houseofcode.dap.server.msa.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import fr.houseofcode.dap.server.msa.EmailController;
import fr.houseofcode.dap.server.msa.google.GmailServiceImpl;

//TODO MSA by Djer |Audit Code| Prend en compte les remarques de CheckStyle
//TODO MSA by Djer |JavaDoc| Commentaire JavaDoc manquant.
/**
 * @author sms
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestingWebApplicationTest {

    @MockBean
    private GmailServiceImpl gmailService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmailController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void exampleTest() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).isEqualTo("Welcome to Spring Boot !");
    }

    @Test
    public void mockExampleTest() throws IOException, GeneralSecurityException {
        // Init
        BDDMockito.given(this.gmailService.getNbUnreadEmail("safia")).willReturn(100);

        //call
        Integer body = this.restTemplate.getForObject("/Email/Unread?userKey=safia", Integer.class);

        //check
        assertThat(body).isEqualTo(100);
    }

}
