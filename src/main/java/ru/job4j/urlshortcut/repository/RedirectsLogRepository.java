package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.model.RedirectsLog;

@Repository
public interface RedirectsLogRepository extends JpaRepository<RedirectsLog, Integer> {
}