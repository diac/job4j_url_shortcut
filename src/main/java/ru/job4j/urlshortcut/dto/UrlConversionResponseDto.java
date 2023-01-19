package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для передачи данных ответа на запрос конвертации URL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlConversionResponseDto {

    /**
     * Уникальный код, полученный при конвертации URL
     */
    private String code;
}