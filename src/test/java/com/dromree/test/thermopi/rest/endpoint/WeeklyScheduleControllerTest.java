package com.dromree.test.thermopi.rest.endpoint;

import com.dromree.thermopi.ThermoPiApplication;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ThermoPiApplication.class)
@WebAppConfiguration
@TestPropertySource("classpath:application.properties")
public class WeeklyScheduleControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void weeklyScheduleTestGetByWeek() throws Exception {
        mockMvc.perform(get("/ThermoPi/WeeklySchedule/0")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.month").value(0))
                .andExpect(jsonPath("$.days").exists())
                .andExpect(jsonPath("$.days").isMap())
                .andExpect(jsonPath("$.days.Monday").isMap())
                .andExpect(jsonPath("$.days.Monday.hours").isMap())
                .andExpect(jsonPath("$.days.Monday.hours.0").isMap())
                .andExpect(jsonPath("$.days.Monday.hours.0.quarters").isMap())
                .andExpect(jsonPath("$.days.Monday.hours.0.quarters.0").isMap())
                .andExpect(jsonPath("$.days.Monday.hours.0.quarters.0.enabled").isBoolean())
        ;
    }

}
