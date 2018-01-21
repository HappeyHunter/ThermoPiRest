package com.dromree.test.thermopi.rest.endpoint;

import com.dromree.thermopi.ThermoPiApplication;
import com.dromree.thermopi.rest.data.BoostData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ThermoPiApplication.class)
@WebAppConfiguration
@TestPropertySource("classpath:application.properties")
public class BoostControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeClass
    public static void beforeClassSetup() {
        // Set up DB?
    }

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void boostControllerTestGet() throws Exception {
        mockMvc.perform(get("/ThermoPi/Boost")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.enabled").exists());
    }

    @Test
    public void boostControllerTestPost() throws Exception {
        BoostData boostData = new BoostData(Boolean.TRUE, null);

        String content = new ObjectMapper().writeValueAsString(boostData);

        mockMvc.perform(post("/ThermoPi/Boost")
                        .accept(MediaType.APPLICATION_JSON_UTF8).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.enabled").value("true"));
    }

}
