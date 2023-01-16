package ru.job4j.urlshortcut.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.job4j.urlshortcut.UrlShortcutApplication;
import ru.job4j.urlshortcut.data.entity.Site;
import ru.job4j.urlshortcut.data.entity.Url;
import ru.job4j.urlshortcut.util.Urls;

import javax.persistence.NoResultException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {UrlShortcutApplication.class})
public class SimpleUrlServiceTest {

    @Autowired
    private UrlService urlService;

    @Autowired
    private SiteService siteService;

    @Test
    public void whenConvertThenSuccess() {
        String value = String.valueOf(System.currentTimeMillis());
        Site site = siteService.findByLogin(siteService.register(value).getLogin());
        Url url = urlService.convert(value, site);
        String expectedShortUrl = Urls.idToShortUrl(url.getId());
        assertThat(expectedShortUrl).isEqualTo(url.getShortUrl());
    }

    @Test
    public void whenConvertDuplicateThenThrowException() {
        String value = String.valueOf(System.currentTimeMillis());
        Site site = siteService.findByLogin(siteService.register(value).getLogin());
        urlService.convert(value, site);
        Assertions.assertThrows(
                DataIntegrityViolationException.class,
                () -> urlService.convert(value, site)
        );
    }

    @Test
    public void whenGetFullUrlByShortUrlThenSuccess() {
        String value = String.valueOf(System.currentTimeMillis());
        Site site = siteService.findByLogin(siteService.register(value).getLogin());
        Url url = urlService.convert(value, site);
        String fullUrl = urlService.getFullUrlByShortUrl(url.getShortUrl());
        assertThat(fullUrl).isEqualTo(value);
    }

    @Test
    public void whenGetFullUrlByNonExistentShortUrlThen() {
        Assertions.assertThrows(
                NoResultException.class,
                () -> urlService.getFullUrlByShortUrl("this-url-does-not-exist")
        );
    }
}