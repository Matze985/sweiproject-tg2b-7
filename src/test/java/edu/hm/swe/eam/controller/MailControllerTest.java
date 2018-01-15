package edu.hm.swe.eam.controller;

import edu.hm.swe.eam.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: Mario
 * Date: 18.12.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MailControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamTagShouldReturnTagList() throws Exception {
        this.mockMvc.perform(get(Constants.API_BASE + "/mailAddress").content("test@test.de")).andDo(print()).andExpect(status().isOk());
    }
}
