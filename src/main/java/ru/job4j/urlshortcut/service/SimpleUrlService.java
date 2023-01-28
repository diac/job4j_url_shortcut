package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.UrlRepository;
import ru.job4j.urlshortcut.util.Urls;

import java.util.Optional;

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

    private final SiteService siteService;

    /**
     * Сконвертировать URL в сокращенный формат
     *
     * @param fullUrl Полный (исходный) URL
     * @return Новый объект Url
     */
    @Override
    public Url convert(String fullUrl, Site site) {
        Url url = urlRepository.save(new Url(0, fullUrl, "", site));
        url.setShortUrl(Urls.idToShortUrl(url.getId()));
        return urlRepository.save(url);
    }

    /**
     * Получить объект URL по сокращенному URL
     *
     * @param shortUrl Сокращенный URL
     * @return Optional объекта URL
     */
    @Override
    public Optional<Url> getByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }
}