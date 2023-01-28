package ru.job4j.urlshortcut.controller;

import liquibase.integration.spring.SpringLiquibase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.service.RedirectsLogService;
import ru.job4j.urlshortcut.service.SiteService;
import ru.job4j.urlshortcut.service.UrlService;

import javax.persistence.NoResultException;
import java.security.Principal;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UrlController.class)
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

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private SpringLiquibase liquibase;

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
        Mockito.when(urlService.getByShortUrl(value)).thenReturn(Optional.of(new Url()));
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