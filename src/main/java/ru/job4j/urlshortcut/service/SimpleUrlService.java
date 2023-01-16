package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.data.entity.Site;
import ru.job4j.urlshortcut.data.entity.Url;
import ru.job4j.urlshortcut.data.repository.UrlRepository;
import ru.job4j.urlshortcut.util.Urls;

import javax.persistence.NoResultException;
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
     * Получить полный URL по сокращенному URL
     *
     * @param shortUrl Сокращенный URL
     * @return Полный URL
     */
    @Override
    public String getFullUrlByShortUrl(String shortUrl) {
        Optional<Url> url = urlRepository.findByShortUrl(shortUrl);
        if (url.isEmpty()) {
            throw new NoResultException();
        }
        return url.get().getFullUrl();
    }
}