package ru.job4j.urlshortcut.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.urlshortcut.data.dto.RedirectsStatRecordDto;
import ru.job4j.urlshortcut.data.entity.RedirectsLog;

import java.util.List;

@Repository
public interface RedirectsLogRepository extends JpaRepository<RedirectsLog, Integer> {

    @Query("""            
            SELECT
                new ru.job4j.urlshortcut.data.dto.RedirectsStatRecordDto(
                    rl.url.fullUrl,
            	    COUNT(rl)
                )
            FROM
            	RedirectsLog AS rl
            JOIN
                rl.url
            GROUP BY
                rl.url.fullUrl""")
    List<RedirectsStatRecordDto> statistic();
}