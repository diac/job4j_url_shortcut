package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.data.entity.Site;
import ru.job4j.urlshortcut.data.entity.Url;

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
     * Получить полный URL по сокращенному URL
     *
     * @param shortUrl Сокращенный URL
     * @return Полный URL
     */
    String getFullUrlByShortUrl(String shortUrl);

    /**
     * Получить объект URL по сокращенному URL
     *
     * @param shortUrl Сокращенный URL
     * @return Объект URL
     */
    Url getByShortUrl(String shortUrl);
}