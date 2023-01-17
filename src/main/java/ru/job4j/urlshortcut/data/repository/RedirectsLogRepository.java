package ru.job4j.urlshortcut.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.data.entity.RedirectsLog;

@Repository
public interface RedirectsLogRepository extends CrudRepository<RedirectsLog, Integer> {
}