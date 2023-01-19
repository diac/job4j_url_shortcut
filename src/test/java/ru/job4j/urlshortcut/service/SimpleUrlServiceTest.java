package ru.job4j.urlshortcut.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.repository.UrlRepository;
import ru.job4j.urlshortcut.util.Urls;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SimpleUrlServiceTest {

    private UrlService urlService;

    private SiteService siteService;

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private SiteRepository siteRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        siteService = new SimpleSiteService(siteRepository, passwordEncoder);
        urlService = new SimpleUrlService(urlRepository, siteService);
    }

    @Test
    public void whenConvert() {
        String value = String.valueOf(System.currentTimeMillis());
        Site site = new Site(0, value, value, value);
        Url url = new Url(0, value, value, site);
        Mockito.when(siteRepository.save(site)).thenReturn(site);
        Mockito.when(urlRepository.save(url)).thenReturn(url);
        Site storedSite = siteService.findByLogin(siteService.register(value).getLogin());
        Url storedUrl = urlService.convert(value, storedSite);
        String expectedShortUrl = Urls.idToShortUrl(storedUrl.getId());
        assertThat(expectedShortUrl).isEqualTo(storedUrl.getShortUrl());
    }

    @Test
    public void whenGetFullUrlByShortUrl() {
        String value = String.valueOf(System.currentTimeMillis());
        Site site = new Site(0, value, value, value);
        Url url = new Url(1, value, Urls.idToShortUrl(1), site);
        Mockito.when(siteRepository.save(site)).thenReturn(site);
        Mockito.when(siteRepository.findByLogin(value)).thenReturn(site);
        Mockito.when(urlRepository.save(url)).thenReturn(url);
        Mockito.when(urlRepository.findByShortUrl(url.getShortUrl())).thenReturn(Optional.of(url));
        Site storedSite = siteService.findByLogin(siteService.register(value).getLogin());
        Url storedUrl = urlService.convert(value, storedSite);
        String fullUrl = urlService.getFullUrlByShortUrl(storedUrl.getShortUrl());
        assertThat(fullUrl).isEqualTo(value);
    }
}