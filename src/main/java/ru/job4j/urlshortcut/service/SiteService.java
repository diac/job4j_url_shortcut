package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.dto.SiteDto;
import ru.job4j.urlshortcut.model.Site;

/**
 * Сервис, реализующий логику, связанную с объектами модели Site
 */
public interface SiteService {

    /**
     * Зарегистрировать сайт в системе по доменному имени
     *
     * @param domainName Доменное имя регистрируемого сайта
     * @return Объект SiteDto, содержащий данные нового объекта Site
     */
    SiteDto register(String domainName);

    /**
     * Найти сайт по его логину в системе
     *
     * @param login Логин сайта в системе
     * @return Сайт
     */
    Site findByLogin(String login);
}