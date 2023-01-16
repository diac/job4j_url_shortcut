package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.data.dto.UrlDto;
import ru.job4j.urlshortcut.data.entity.Site;

/**
 * Сервис, реализующий логику, связанную с объектами Url
 */
public interface UrlService {

    /**
     * Сконвертировать URL в сокращенный формат
     * @param fullUrl Полный (исходный) URL
     * @param site Объект-сайт, от имени которого регистрируется новый URL в системе
     * @return Объект UrlDto, содержаний данные нового объекта Url
     */
    UrlDto convert(String fullUrl, Site site);
}