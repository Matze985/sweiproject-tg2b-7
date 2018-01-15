package edu.hm.swe.eam.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static edu.hm.swe.eam.Constants.API_BASE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Matthias Rude on 12.12.2017.
 * Email: Matze.development@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActivityControllerTest {

    private String testActivityName = "TestActivity923478523940237529304";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamActivityShouldReturnActivityList() throws Exception {
        this.mockMvc.perform(get(API_BASE + "/activity")).andDo(print()).andExpect(status().isOk());
//                .andExpect(jsonPath("$.content").value("Hello, World!"));
    }

    @Test
    public void addActivity() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(
                post(API_BASE + "/activity")
                        .content("{\"title\":\"" + testActivityName + "\",\"text\":\"test test\",\"tags\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        int id = extractID(response);
        mockMvc.perform(delete(API_BASE + "/activity/" + id));
    }

    @Test
    public void updateActivity() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(
                post(API_BASE + "/activity")
                        .content("{\"title\":\"" + testActivityName + "\",\"text\":\"test test\",\"tags\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        int id = extractID(response);
        MockHttpServletResponse responseUpdate = this.mockMvc.perform(
                post(API_BASE + "/activity")
                        .content("{\"id\":\"" + id + "\",\"title\":\"" + testActivityName + "\",\"text\":\"test test\",\"tags\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        int idUpdate = extractID(responseUpdate);
        assertEquals(id, idUpdate);

        mockMvc.perform(delete(API_BASE + "/activity/" + id));
    }

    @Test
    public void deleteActivity() throws Exception {
        MockHttpServletResponse response = this.mockMvc.perform(
                post(API_BASE + "/activity")
                        .content("{\"title\":\"" + testActivityName + "\",\"text\":\"test test\",\"tags\":[]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        int id = extractID(response);
        mockMvc.perform(delete(API_BASE + "/activity/" + id)).andExpect(status().isOk());
    }


    private int extractID(MockHttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode content = mapper.readTree(response.getContentAsString());
        assertNotNull(content);
        assertNotNull(content.findValue("id"));

        return content.findValue("id").asInt();
    }
}

