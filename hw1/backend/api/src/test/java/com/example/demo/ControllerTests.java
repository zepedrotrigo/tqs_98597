package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.GreaterThan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsNot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import static org.hamcrest.CoreMatchers.*;

@WebMvcTest(Controller.class)
public class ControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    void testTotalReport() throws Exception {
        String date = "2022-04-04";

        mvc.perform(get("/")
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.date", is("2022-04-04")))
                .andExpect(jsonPath("$.data.last_update", is("2022-04-05 04:20:48")))
                .andExpect(jsonPath("$.data.confirmed", is(493673448)))
                .andExpect(jsonPath("$.data.confirmed_diff", is(2232805)))
                .andExpect(jsonPath("$.data.deaths", is(6169905)))
                .andExpect(jsonPath("$.data.deaths_diff", is(17018)))
                .andExpect(jsonPath("$.data.recovered", is(0)))
                .andExpect(jsonPath("$.data.recovered_diff", is(0)))
                .andExpect(jsonPath("$.data.active", is(487503543)))
                .andExpect(jsonPath("$.data.active_diff", is(2215787)))
                .andExpect(jsonPath("$.data.fatality_rate", is(0.0125)));
    }

    @Test
    public void testTotalReportInvalidParams() throws Exception {
        String date = "20220404";
        mvc.perform(get("/")
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error.date[0]", is("The date does not match the format Y-m-d.")));
    }

    @Test
    public void testGetCountriesList() throws Exception {
        mvc.perform(get("/v1/countries_list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results").isNumber())
                .andExpect(jsonPath("$.response").isNotEmpty());
    }

    @Test
    public void testGetCountryStats() throws Exception {
        String date = "2022-04-04";
        String country = "Portugal";

        mvc.perform(get("/v1/country_stats")
                .param("country", country)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").isEmpty())
                .andExpect(jsonPath("$.response").isNotEmpty());

        mvc.perform(get("/v1/country_stats")
                .param("country", country)
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errors").isEmpty())
                .andExpect(jsonPath("$.response").isNotEmpty());
    }

    @Test
    public void testGetCountryStatsInvalidParams() throws Exception {
        String date = "20220404";
        String country = "Portugala";

        mvc.perform(get("/v1/country_stats")
                .param("country", country)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").isEmpty());

        mvc.perform(get("/v1/country_stats")
                .param("country", country)
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors.day", is("The Day field must contain a valid date: YYYY-MM-DD.")));

    }

    @Test
    public void testGetCacheStats() throws Exception {
        mvc.perform(get("/v1/cache")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hits").isNumber())
                .andExpect(jsonPath("$.misses").isNumber())
                .andExpect(jsonPath("$.requests").isNumber());
    }
}
