package edu.hm.swe.eam.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static edu.hm.swe.eam.Constants.API_BASE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Matthias Rude on 18.12.2017.
 * Email: Matze.development@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {
    @Value("${gmail.username}")
    String gmailUsername;
    @Value("${gmail.password}")
    String gmailPassword;
    @Autowired
    private MockMvc mockMvc;

    /**
     * implementiere variablen test hier, weil es die Annotation @SpringBootTest benötigt um auf konfigurierte
     * Variablen zuzugreifen.
     */
    @Test
    public void testSystemEnvironmentalVariables() {
        Assert.assertEquals("sweiproject.tg2b.7@gmail.com", gmailUsername);
        Assert.assertNotNull(gmailPassword);
    }

    @Test
    public void testUserInput() throws Exception {
        String contact_title = "testTitle";
        String contact_description = "testDescription";
        String contact_email = "testMail";

        mockMvc.perform(get(API_BASE + "/contact_mailAddress")
                .param("contact_title", contact_title)
                .param("contact_description", contact_description)
                .param("contact_email", contact_email)
        ).andDo(print())
                .andExpect(status().isOk());
    }
}
