package ru.job4j.urlshortcut.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.urlshortcut.model.RedirectsLog;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.RedirectsLogRepository;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.repository.UrlRepository;
import ru.job4j.urlshortcut.util.StringGenerator;
import ru.job4j.urlshortcut.util.Urls;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SimpleRedirectsLogServiceTest {

    private RedirectsLogService redirectsLogService;

    private UrlService urlService;

    private SiteService siteService;

    @Mock
    private RedirectsLogRepository redirectsLogRepository;

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private SiteRepository siteRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private StringGenerator stringGenerator;

    @BeforeEach
    public void init() {
        redirectsLogService = new SimpleRedirectsLogService(redirectsLogRepository);
        siteService = new SimpleSiteService(siteRepository, passwordEncoder, stringGenerator);
        urlService = new SimpleUrlService(urlRepository, siteService);
        Mockito.when(stringGenerator.generate()).thenReturn(String.valueOf(System.currentTimeMillis()).toCharArray());
    }

    @Test
    public void whenRegister() {
        String value = String.valueOf(System.currentTimeMillis());
        Site site = siteService.findByLogin(siteService.register(value).getLogin());
        Url url = new Url(1, value, Urls.idToShortUrl(1), site);
        RedirectsLog redirectsLog = new RedirectsLog(0, value, LocalDateTime.now(), url);
        Mockito.when(urlRepository.save(new Url(0, value, "", site))).thenReturn(url);
        Mockito.when(redirectsLogRepository.save(redirectsLog)).thenReturn(redirectsLog);
        Mockito.when(redirectsLogRepository.findAll()).thenReturn(List.of(redirectsLog));
        Url storedUrl = urlService.convert(value, site);
        RedirectsLog storedRedirectsLog = redirectsLogService.register(storedUrl, value);
        Assertions.assertThat(redirectsLogService.findAll()).contains(storedRedirectsLog);
    }
}