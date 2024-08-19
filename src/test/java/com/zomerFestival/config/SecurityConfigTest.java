package com.zomerFestival.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAccessToLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    void testAccessToHomeWithoutLogin() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void testAccessToKoopticketWithoutLogin() throws Exception {
        mockMvc.perform(get("/ticket"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void testAccessToWijzigPlanningWithoutLogin() throws Exception {
        mockMvc.perform(get("/wijzigplanning/{festivalId}", 8))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(roles = { "USER" })
    void testAccessToWijzigPlanningWithUserRole() throws Exception {
        mockMvc.perform(get("/wijzigplanning/{festivalId}", 8))
                .andExpect(status().isForbidden());
    }

    @Test
    void testAccessToApiWithoutLogin() throws Exception {
        mockMvc.perform(get("/rest/artiest/festival/{id}", 1))
                .andExpect(status().isOk());
    }
}