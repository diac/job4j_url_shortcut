package ru.job4j.urlshortcut.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Глобальный обработчик исключений
 */
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    /**
     * Объект-логгер
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class.getSimpleName());

    /**
     * Маппер объектов
     */
    private final ObjectMapper objectMapper;

    /**
     * Метод, в котором осуществляется глобальная обработка исключений
     *
     * @param e Обрабатываемое исключение
     * @param request Входящий HTTP запрос
     * @param response HTTP ответ, в который в случае выброса исключения добавляется информация об исключении
     * @throws IOException В случае, если возникает ошибка записи в response
     */
    @ExceptionHandler(
            value = {
                    NullPointerException.class,
                    DataIntegrityViolationException.class
            }
    )
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        LOGGER.error(e.getLocalizedMessage());
    }
}