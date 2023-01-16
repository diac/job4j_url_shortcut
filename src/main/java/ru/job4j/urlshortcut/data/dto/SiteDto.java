package ru.job4j.urlshortcut.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для модели данных Site
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteDto {

    /**
     * Доменное имя сайта
     */
    private String domainName;

    /**
     * Логин сайта в системе
     */
    private String login;

    /**
     * Пароль сайта в системе
     */
    private char[] password;
}