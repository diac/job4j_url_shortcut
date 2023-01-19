package ru.job4j.urlshortcut.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.job4j.urlshortcut.UrlShortcutApplication;
import ru.job4j.urlshortcut.model.RedirectsLog;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;

@SpringBootTest(classes = {UrlShortcutApplication.class})
public class SimpleRedirectsLogServiceTest {

    @Autowired
    private RedirectsLogService redirectsLogService;

    @Autowired
    private UrlService urlService;

    @Autowired
    private SiteService siteService;

    @Test
    public void whenRegisterThenSuccess() {
        String value = String.valueOf(System.currentTimeMillis());
        Site site = siteService.findByLogin(siteService.register(value).getLogin());
        Url url = urlService.convert(value, site);
        RedirectsLog redirectsLog = redirectsLogService.register(url, value);
        Assertions.assertThat(redirectsLogService.findAll()).contains(redirectsLog);
    }

    @Test
    public void whenRegisterForTransientUrlThenThrowException() {
        String value = String.valueOf(System.currentTimeMillis());
        Url url = new Url(0, value, value, new Site());
        org.junit.jupiter.api.Assertions.assertThrows(
                DataIntegrityViolationException.class,
                () -> redirectsLogService.register(url, value)
        );
    }
}