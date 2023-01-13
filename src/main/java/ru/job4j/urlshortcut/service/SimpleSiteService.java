package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.data.dto.SiteDto;
import ru.job4j.urlshortcut.data.entity.Site;
import ru.job4j.urlshortcut.data.repository.SiteRepository;
import ru.job4j.urlshortcut.util.Passwords;

import java.nio.CharBuffer;

@Service
@AllArgsConstructor
public class SimpleSiteService implements SiteService {

    private final SiteRepository siteRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public SiteDto register(String domainName) {
        Site site = new Site();
        site.setDomainName(domainName);
        site.setLogin(domainName);
        char[] rawPassword = Passwords.generate();
        site.setPassword(passwordEncoder.encode(CharBuffer.wrap(rawPassword)).toCharArray());
        siteRepository.save(site);
        return new SiteDto(site.getDomainName(), site.getLogin(), rawPassword);
    }
}