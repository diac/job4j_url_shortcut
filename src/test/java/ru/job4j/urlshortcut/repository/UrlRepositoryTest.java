package ru.job4j.urlshortcut.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;

import java.util.Optional;

@DataJpaTest
public class UrlRepositoryTest {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Test
    public void whenFindByShortUrl() {
        String value = String.valueOf(System.currentTimeMillis());
        Site site = siteRepository.save(new Site(0, value, value, value));
        Url url = urlRepository.save(new Url(0, value, value, site));
        Optional<Url> foundUrl = urlRepository.findByShortUrl(url.getShortUrl());
        Assertions.assertThat(foundUrl).isNotEmpty();
        Assertions.assertThat(foundUrl.get()).isEqualTo(url);
    }
}