package ru.job4j.urlshortcut.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.urlshortcut.UrlShortcutApplication;
import ru.job4j.urlshortcut.service.SiteService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UrlShortcutApplication.class)
@AutoConfigureMockMvc
public class SiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiteService siteService;

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