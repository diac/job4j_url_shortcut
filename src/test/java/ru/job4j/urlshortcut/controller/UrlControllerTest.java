package ru.job4j.urlshortcut.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.urlshortcut.UrlShortcutApplication;
import ru.job4j.urlshortcut.data.entity.Site;
import ru.job4j.urlshortcut.data.entity.Url;
import ru.job4j.urlshortcut.service.RedirectsLogService;
import ru.job4j.urlshortcut.service.SiteService;
import ru.job4j.urlshortcut.service.UrlService;

import javax.persistence.NoResultException;
import java.security.Principal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UrlShortcutApplication.class)
@AutoConfigureMockMvc
public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService urlService;

    @MockBean
    private SiteService siteService;

    @MockBean
    private RedirectsLogService redirectsLogService;

    @Test
    @WithMockUser
    public void whenConvertThenStatusIsCreated() throws Exception {
        String value = String.valueOf(System.currentTimeMillis());
        Principal principal = Mockito.mock(Principal.class);
        Site site = siteService.findByLogin(principal.getName());
        String requestBody = String.format("{\"url\": %s}", value);
        Mockito.when(urlService.convert(value, site)).thenReturn(new Url());
        mockMvc.perform(
                post("/convert")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void whenConvertAndUrlIsBlankThenStatusIsBadRequest() throws Exception {
        String requestBody = "{\"url\": \"\"}";
        mockMvc.perform(
                post("/convert")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void whenConvertAndRequestBodyIsEmptyThenStatusIsBadRequest() throws Exception {
        mockMvc.perform(post("/convert"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenConvertUnauthorizedThenStatusIsForbidden() throws Exception {
        mockMvc.perform(post("/convert"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void whenRedirectThenStatusIsFound() throws Exception {
        String value = String.valueOf(System.currentTimeMillis());
        Mockito.when(urlService.getByShortUrl(value)).thenReturn(new Url());
        String requestUrl = String.format("/redirect/%s", value);
        mockMvc.perform(get(requestUrl))
                .andExpect(status().isFound());
    }

    @Test
    public void whenRedirectToNonExistentUrlThenStatusIsNotFound() throws Exception {
        String value = String.valueOf(System.currentTimeMillis());
        Mockito.when(urlService.getByShortUrl(value)).thenThrow(NoResultException.class);
        String requestUrl = String.format("/redirect/%s", value);
        mockMvc.perform(get(requestUrl))
                .andExpect(status().isNotFound());
    }
}