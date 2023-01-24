package ru.job4j.urlshortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *
 * Модель данных "Сайт"
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Site {

    /**
     * Идентификатор сайта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /**
     * Доменное имя сайта
     */
    @Column(name = "domain_name")
    private String domainName;

    /**
     * Логин сайта в системе
     */
    private String login;

    /**
     * Пароль сайта в системе
     */
    private String password;
}