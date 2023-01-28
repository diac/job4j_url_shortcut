package ru.job4j.urlshortcut.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.urlshortcut.dto.SiteDto;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.util.StringGenerator;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SimpleSiteServiceTest {

    private SiteService siteService;

    @Mock
    private SiteRepository siteRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private StringGenerator stringGenerator;

    @BeforeEach
    private void init() {
        siteService = new SimpleSiteService(siteRepository, passwordEncoder, stringGenerator);
        Mockito.when(stringGenerator.generate()).thenReturn(String.valueOf(System.currentTimeMillis()).toCharArray());
    }

    @Test
    public void whenRegister() {
        String value = String.valueOf(System.currentTimeMillis());
        SiteDto siteDto = siteService.register(value);
        assertThat(siteDto.getDomainName()).isEqualTo(value);
        assertThat(siteDto.getLogin()).isEqualTo(value);
    }
}