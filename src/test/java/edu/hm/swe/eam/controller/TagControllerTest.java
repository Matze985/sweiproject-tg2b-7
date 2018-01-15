package edu.hm.swe.eam.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static edu.hm.swe.eam.Constants.API_BASE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Matthias Rude on 18.12.2017.
 * Email: Matze.development@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest {
    private String testTag = "tag12372934875823458709";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamTagShouldReturnTagList() throws Exception {
        this.mockMvc.perform(get(API_BASE + "/tag")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addTag() throws Exception {
        this.mockMvc.perform(
                post(API_BASE + "/tag")
                        .content("[\"" + testTag + "\"]")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
