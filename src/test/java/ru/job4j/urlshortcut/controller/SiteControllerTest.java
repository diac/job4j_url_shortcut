package ru.job4j.urlshortcut.controller;

import liquibase.integration.spring.SpringLiquibase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.urlshortcut.service.SiteService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SiteController.class)
@AutoConfigureMockMvc
public class SiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiteService siteService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private SpringLiquibase liquibase;

    @Test
    public void whenRegistrationThenStatusIsCreated() throws Exception {
        String value = String.valueOf(System.currentTimeMillis());
        String requestBody = String.format("{\"site\": %s}", value);
        mockMvc.perform(
                post("/registration")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    public void whenRegistrationAndBlankSiteThenStatusIsBadRequest() throws Exception {
        String requestBody = "{\"site\": \"\"}";
        mockMvc.perform(
                post("/registration")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void whenRegistrationAndEmptyRequestBodyThenStatusIsBadRequest() throws Exception {
        mockMvc.perform(post("/registration"))
                .andExpect(status().isBadRequest());
    }
}