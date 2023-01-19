package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.dto.RedirectsStatRecordDto;
import ru.job4j.urlshortcut.model.RedirectsLog;
import ru.job4j.urlshortcut.model.Url;

import java.util.List;

/**
 * Сервис, реализующий логику, связанную с объектами модели RedirectsLog
 */
public interface RedirectsLogService {

    /**
     * Зарегистрировать событие редиреркта
     *
     * @param url             URL, на который был осуществлен редирект
     * @param clientIpAddress IP адрес клиента, перешедшего по сокращенной ссылке
     * @return Сохраненная запись RedirectsLog
     */
    RedirectsLog register(Url url, String clientIpAddress);

    /**
     * Получить все записи статистики редиректов
     *
     * @return Список всех записей статистики редиректов
     */
    List<RedirectsLog> findAll();

    /**
     * Сгенерировать статистический отчет по количеству редиректов для каждого URL
     *
     * @return Перечень записей статистики редиректов
     */
    List<RedirectsStatRecordDto> statistic();
}