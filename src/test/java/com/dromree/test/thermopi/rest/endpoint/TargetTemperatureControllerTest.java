package com.dromree.test.thermopi.rest.endpoint;

import com.dromree.thermopi.ThermoPiApplication;
import com.dromree.thermopi.rest.data.TargetTemperatureData;
import com.dromree.thermopi.rest.endpoint.TargetTemperatureController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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

import static junit.framework.TestCase.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ThermoPiApplication.class)
@WebAppConfiguration
@TestPropertySource("classpath:application.properties")
public class TargetTemperatureControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void targetTemperatureTestGet() throws Exception {
        mockMvc.perform(get("/ThermoPi/TargetTemperature")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.temperature").exists());
    }

    @Test
    public void targetTemperatureTestPut() throws Exception {
        TargetTemperatureData targetTemperatureData = new TargetTemperatureData(20d);

        String content = new ObjectMapper().writeValueAsString(targetTemperatureData);

        mockMvc.perform(put("/ThermoPi/TargetTemperature")
                .content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}
