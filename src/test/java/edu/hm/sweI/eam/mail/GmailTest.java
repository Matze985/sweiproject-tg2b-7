package edu.hm.sweI.eam.mail;

import com.sun.javafx.binding.SelectBinding;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import static edu.hm.sweI.eam.Constants.API_BASE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc


@PropertySource("classpath:application-dev.properties")

public class GmailTest {

    @Autowired
    private MockMvc mockMvc;

    @Resource
    private Environment environment;

    @Test
    public void testSystemEnvironmentalVariables() throws Exception {

        Assert.assertEquals(environment.getProperty("GMAIL_USERNAME"), "sweiproject.tg2b.7@gmail.com");
        Assert.assertEquals(environment.getProperty("GMAIL_PASSWORD"), "softwareengineering1");
    }

    @Test
    @RequestMapping("/api/contact_mailAddress")
    public void testUserInput(
        @RequestParam(value = "contact_title") String contact_title,
        @RequestParam(value = "contact_description") String contact_description,
        @RequestParam(value = "contact_email") String contact_email) throws  Exception{

        Assert.assertNotNull(contact_title);
        Assert.assertNotNull(contact_description);
        Assert.assertNotNull(contact_email);
    }
}
