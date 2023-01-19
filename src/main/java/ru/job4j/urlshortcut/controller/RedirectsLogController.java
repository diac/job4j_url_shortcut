package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.RedirectsStatRecordDto;
import ru.job4j.urlshortcut.service.RedirectsLogService;

import java.util.List;

/**
 * Контроллер, реализующий доступ к объектам модели RedirectsLog через REST
 */
@RestController
@AllArgsConstructor
public class RedirectsLogController {

    /**
     * Сервис, реализующий логику работы с объектами модели RedirectsLog
     */
    private final RedirectsLogService redirectsLogService;

    /**
     * Сгенерировать статистический отчет по количеству редиректов для каждого URL
     *
     * @return Перечень записей статистики редиректов
     */
    @GetMapping("/statistic")
    public ResponseEntity<List<RedirectsStatRecordDto>> statistic() {
        return new ResponseEntity<>(
                redirectsLogService.statistic(),
                HttpStatus.OK
        );
    }
}