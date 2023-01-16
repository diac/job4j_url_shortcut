package ru.job4j.urlshortcut.service;

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
    Url convert(String fullUrl);
}