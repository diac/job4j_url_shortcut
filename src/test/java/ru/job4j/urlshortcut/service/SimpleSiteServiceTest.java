package ru.job4j.urlshortcut.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import ru.job4j.urlshortcut.UrlShortcutApplication;
import ru.job4j.urlshortcut.dto.SiteDto;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = {UrlShortcutApplication.class})
public class SimpleSiteServiceTest {

    @Autowired
    private SiteService siteService;

    @Test
    public void whenRegisterThenSuccess() {
        String value = String.valueOf(System.currentTimeMillis());
        SiteDto siteDto = siteService.register(value);
        assertThat(siteDto.getDomainName()).isEqualTo(value);
        assertThat(siteDto.getLogin()).isEqualTo(value);
    }

    @Test
    public void whenRegisterDuplicateThenException() {
        String value = String.valueOf(System.currentTimeMillis());
        siteService.register(value);
        Assertions.assertThrows(
                DataIntegrityViolationException.class,
                () -> siteService.register(value)
        );
    }
}