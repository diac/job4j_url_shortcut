package ru.job4j.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.dto.RedirectsStatRecordDto;
import ru.job4j.urlshortcut.model.RedirectsLog;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.repository.RedirectsLogRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис, реализующий логику, связанную с объектами модели RedirectsLog
 */
@Service
@AllArgsConstructor
public class SimpleRedirectsLogService implements RedirectsLogService {

    /**
     * Репозиторий, через который осуществляется доступ к данным модели RedirectsLog в БД
     */
    private final RedirectsLogRepository redirectsLogRepository;

    /**
     * Зарегистрировать событие редиреркта
     *
     * @param url             URL, на который был осуществлен редирект
     * @param clientIpAddress IP адрес клиента, перешедшего по сокращенной ссылке
     * @return Сохраненная запись RedirectsLog
     */
    @Override
    public RedirectsLog register(Url url, String clientIpAddress) {
        RedirectsLog redirectsLog = new RedirectsLog();
        redirectsLog.setUrl(url);
        redirectsLog.setDateTime(LocalDateTime.now());
        redirectsLog.setClientIpAddress(clientIpAddress);
        return redirectsLogRepository.save(redirectsLog);
    }

    /**
     * Получить все записи статистики редиректов
     *
     * @return Список всех записей статистики редиректов
     */
    @Override
    public List<RedirectsLog> findAll() {
        return new ArrayList<>(redirectsLogRepository.findAll());
    }

    /**
     * Сгенерировать статистический отчет по количеству редиректов для каждого URL
     *
     * @return Перечень записей статистики редиректов
     */
    @Override
    public List<RedirectsStatRecordDto> statistic() {
        List<RedirectsLog> redirectsLogs = redirectsLogRepository.findAll();
        Map<Url, Integer> resultMap = new HashMap<>();
        redirectsLogs.forEach(redirectsLog -> {
            resultMap.putIfAbsent(redirectsLog.getUrl(), 0);
            resultMap.put(redirectsLog.getUrl(), resultMap.get(redirectsLog.getUrl()) + 1);
        });
        return resultMap.entrySet().stream()
                .map(resultMapEntry ->
                        new RedirectsStatRecordDto(
                                resultMapEntry.getKey().getFullUrl(),
                                resultMapEntry.getValue()
                        )
                ).toList();
    }
}