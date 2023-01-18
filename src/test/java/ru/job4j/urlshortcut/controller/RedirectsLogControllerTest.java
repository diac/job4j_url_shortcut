package ru.job4j.urlshortcut.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.urlshortcut.UrlShortcutApplication;
import ru.job4j.urlshortcut.service.RedirectsLogService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UrlShortcutApplication.class)
@AutoConfigureMockMvc
public class RedirectsLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RedirectsLogService redirectsLogService;

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