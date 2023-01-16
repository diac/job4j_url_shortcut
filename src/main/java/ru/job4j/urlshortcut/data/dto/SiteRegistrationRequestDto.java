package ru.job4j.urlshortcut.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для обработки входящих запросов на регистрацию сайта
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteRegistrationRequestDto {

    /**
     * Доменное имя сайта
     */
    private String site;
}