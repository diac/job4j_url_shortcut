package ru.job4j.urlshortcut.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.job4j.urlshortcut.model.Site;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SiteRepositoryTest {

    @Autowired
    private SiteRepository siteRepository;

    @Test
    public void whenFindByLogin() {
        String value = String.valueOf(System.currentTimeMillis());
        Site site = siteRepository.save(new Site(0, value, value, value));
        Site siteInDb = siteRepository.findByLogin(value);
        assertThat(site).isEqualTo(siteInDb);
    }
}