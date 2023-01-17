package ru.job4j.urlshortcut.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Модель данных "Статистика редиректов"
 */
@Entity
@Table(name = "redirects_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RedirectsLog {

    /**
     * Идентификатор записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * IP адрес клиента, перешедшего по сокращенной ссылке
     */
    @Column(name = "client_ip_address")
    private String clientIpAddress;

    /**
     * Временная метка зарегистрированного редиректа
     */
    @Column(name = "datetime")
    private LocalDateTime dateTime;

    /**
     * URL, на который был осуществлен редирект
     */
    @ManyToOne
    @JoinColumn(name = "url_id")
    private Url url;
}