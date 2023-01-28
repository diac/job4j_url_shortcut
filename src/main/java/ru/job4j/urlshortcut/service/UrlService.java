package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;

import java.util.Optional;

/**
 * Сервис, реализующий логику, связанную с объектами Url
 */
public interface UrlService {

    /**
     * Сконвертировать URL в сокращенный формат
     *
     * @param fullUrl Полный (исходный) URL
     * @return Новый объект Url
     */
    Url convert(String fullUrl, Site site);

    /**
     * Получить объект URL по сокращенному URL
     *
     * @param shortUrl Сокращенный URL
     * @return Optional объекта URL
     */
    Optional<Url> getByShortUrl(String shortUrl);
}