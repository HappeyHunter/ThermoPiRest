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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ThermoPiApplication.class)
@WebAppConfiguration
@TestPropertySource("classpath:application.properties")
public class HolidayControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void holidaysTestGet() throws Exception {
        mockMvc.perform(get("/ThermoPi/Holidays")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void holidaysTestPostGetById() throws Exception {
        /*long startDate = System.currentTimeMillis();
        long endDate = System.currentTimeMillis()+7*24*60*60*1000;

        HolidayData holidayData = new HolidayData(null, startDate, endDate);

        String content = new ObjectMapper().writeValueAsString(holidayData);

        String resultContent = mockMvc.perform(post("/ThermoPi/Holidays")
                .accept(MediaType.APPLICATION_JSON_UTF8).content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.startDate").value(startDate))
                .andExpect(jsonPath("$.endDate").value(endDate))
                .andReturn().getResponse().getContentAsString();

        HolidayData holidayDataResponse = new ObjectMapper().readValue(resultContent, HolidayData.class);

        mockMvc.perform(get("/ThermoPi/Holidays/" + holidayDataResponse.getHolidayID())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.startDate").value(startDate))
                .andExpect(jsonPath("$.endDate").value(endDate));*/
    }


}
