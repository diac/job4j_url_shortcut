package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.SiteDto;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;
import ru.job4j.urlshortcut.util.StringGenerator;

import java.nio.CharBuffer;

/**
 * Сервис, реализующий логику, связанную с объектами модели Site
 */
@Service
@AllArgsConstructor
public class SimpleSiteService implements SiteService {

    /**
     * Репозиторий, через который осуществляется доступ к данным модели Site в БД
     */
    private final SiteRepository siteRepository;

    /**
     * Класс-шифровальщик паролей
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Генератор строк
     */
    private final StringGenerator stringGenerator;

    /**
     * Зарегистрировать сайт в системе по доменному имени
     *
     * @param domainName Доменное имя регистрируемого сайта
     * @return Объект SiteDto, содержащий данные нового объекта Site
     */
    @Override
    public SiteDto register(String domainName) {
        Site site = new Site();
        site.setDomainName(domainName);
        site.setLogin(domainName);
        char[] rawPassword = stringGenerator.generate();
        site.setPassword(passwordEncoder.encode(CharBuffer.wrap(rawPassword)));
        siteRepository.save(site);
        return new SiteDto(site.getDomainName(), site.getLogin(), rawPassword);
    }

    /**
     * Найти сайт по его логину в системе
     *
     * @param login Логин сайта в системе
     * @return Сайт
     */
    @Override
    public Site findByLogin(String login) {
        return siteRepository.findByLogin(login);
    }
}