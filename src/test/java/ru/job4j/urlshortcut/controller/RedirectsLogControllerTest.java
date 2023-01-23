package ru.job4j.urlshortcut.controller;

import liquibase.integration.spring.SpringLiquibase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.urlshortcut.service.RedirectsLogService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RedirectsLogController.class)
@AutoConfigureMockMvc
public class RedirectsLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RedirectsLogService redirectsLogService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private SpringLiquibase liquibase;

    @Test
    @WithMockUser
    public void whenStatisticThenStatusIsOk() throws Exception {
        mockMvc.perform(get("/statistic"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenStatisticUnauthorizedThenStatusIsForbidden() throws Exception {
        mockMvc.perform(get("/statistic"))
                .andExpect(status().isForbidden());
    }
}