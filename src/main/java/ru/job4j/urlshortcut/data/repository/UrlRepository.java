package ru.job4j.urlshortcut.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.data.entity.Url;

@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {

    Url findByShortUrl(String shortUrl);
}