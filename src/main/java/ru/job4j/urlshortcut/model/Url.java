package ru.job4j.urlshortcut.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Модель данных "URL"
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Url {

    /**
     * Идентификатор URL
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Полный (исходный) URL
     */
    @Column(name = "full_url")
    private String fullUrl;

    /**
     * Сокращенный URL
     */
    @Column(name = "short_url")
    private String shortUrl;

    /**
     * Сайт, которому принадлежит URL
     */
    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;
}