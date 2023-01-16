package ru.job4j.urlshortcut.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для обработки входящих запросов на конвертацию URL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlConversionRequestDto {

    /**
     * URL
     */
    private String url;
}