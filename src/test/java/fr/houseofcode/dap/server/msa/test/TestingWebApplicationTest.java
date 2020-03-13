/**
 * 
 */
package fr.houseofcode.dap.server.msa.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.houseofcode.dap.server.msa.EmailController;

/**
 * @author sms
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestingWebApplicationTest {

    @Autowired
    private EmailController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

}
