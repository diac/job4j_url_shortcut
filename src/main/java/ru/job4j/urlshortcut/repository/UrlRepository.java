package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.model.Url;

import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {

    Optional<Url> findByShortUrl(String shortUrl);
}