package ru.job4j.urlshortcut.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO Для модели данных Url
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto {

    /**
     * Полный (исходный) URL
     */
    private String fullUrl;

    /**
     * Сокращенный URL
     */
    private String shortUrl;
}