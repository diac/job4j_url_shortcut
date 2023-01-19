package ru.job4j.urlshortcut.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.job4j.urlshortcut.model.RedirectsLog;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RedirectsLogRepositoryTest {

    @Autowired
    private RedirectsLogRepository redirectsLogRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Test
    public void whenStatistic() {
        String value = String.valueOf(System.currentTimeMillis());
        Site site = siteRepository.save(new Site(0, value, value, value));
        Url url1 = urlRepository.save(new Url(0, value + "_1", value + "_1", site));
        Url url2 = urlRepository.save(new Url(0, value + "_2", value + "_2", site));
        Url url3 = urlRepository.save(new Url(0, value + "_3", value + "_3", site));
        RedirectsLog redirectsLog1 = new RedirectsLog(1, value, LocalDateTime.now(), url1);
        RedirectsLog redirectsLog2 = new RedirectsLog(2, value, LocalDateTime.now(), url2);
        RedirectsLog redirectsLog3 = new RedirectsLog(3, value, LocalDateTime.now(), url3);
        redirectsLogRepository.save(redirectsLog1);
        redirectsLogRepository.save(redirectsLog2);
        redirectsLogRepository.save(redirectsLog3);
        Iterable<RedirectsLog> redirectsLogs = redirectsLogRepository.findAll();
        assertThat(redirectsLogs).hasSize(3).contains(redirectsLog1, redirectsLog2, redirectsLog3);
    }
}