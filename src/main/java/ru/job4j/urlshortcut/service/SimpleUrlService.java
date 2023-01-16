package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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

    private final SiteService siteService;

    /**
     * Сконвертировать URL в сокращенный формат
     *
     * @param fullUrl Полный (исходный) URL
     * @return Новый объект Url
     */
    @Override
    public Url convert(String fullUrl) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(principal);
        Site site = siteService.findByLogin(
                ((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        );
        System.out.println(site);
        Url url = urlRepository.save(new Url(0, fullUrl, "", site));
        url.setShortUrl(Urls.idToShortUrl(url.getId()));
        return urlRepository.save(url);
    }
}