package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для отдельной записи статистики редиректов
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedirectsStatRecordDto {

    /**
     * Зарегистрированный в системе URL
     */
    private String url;

    /**
     * Общее количество редиректов, выполненных в системе для конкретного URL
     */
    private long total;
}