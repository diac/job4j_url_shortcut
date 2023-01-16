package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.data.dto.SiteDto;

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
}