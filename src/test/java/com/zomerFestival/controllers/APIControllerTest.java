package com.zomerFestival.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class APIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetArtiestenPerFestival() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/artiest/festival/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetFestivalsByGenres() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rest/festivals/genre/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}