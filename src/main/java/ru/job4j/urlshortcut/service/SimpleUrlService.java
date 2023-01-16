package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.data.dto.UrlDto;
import ru.job4j.urlshortcut.data.entity.Site;
import ru.job4j.urlshortcut.data.entity.Url;
import ru.job4j.urlshortcut.data.repository.UrlRepository;
import ru.job4j.urlshortcut.util.Urls;

/**
 * Сервис, реализующий логику, связанную с объектами Url
 */
@Service
@AllArgsConstructor
public class SimpleUrlService implements UrlService {

    /**
     * Репозиторий, через который осуществляется доступ к данным модели Site в БД
     */
    private final UrlRepository urlRepository;

    /**
     * Сконвертировать URL в сокращенный формат
     *
     * @param fullUrl Полный (исходный) URL
     * @return Объект UrlDto, содержаний данные нового объекта Url
     */
    @Override
    public UrlDto convert(String fullUrl, Site site) {
        Url url = urlRepository.save(new Url(0, fullUrl, null, site));
        url.setShortUrl(Urls.idToShortUrl(url.getId()));
        urlRepository.save(url);
        return new UrlDto(url.getFullUrl(), url.getShortUrl());
    }
}