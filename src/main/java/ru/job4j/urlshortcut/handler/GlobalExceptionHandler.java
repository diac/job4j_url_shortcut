package ru.job4j.urlshortcut.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     * Метод, в котором осуществляется глобальная обработка общих исключений
     *
     * @param e        Обрабатываемое исключение
     * @param request  Входящий HTTP запрос
     * @param response HTTP ответ, в который в случае выброса исключения добавляется информация об исключении
     * @throws IOException В случае, если возникает ошибка записи в response
     */
    @ExceptionHandler(
            value = {
                    NullPointerException.class,
                    DataIntegrityViolationException.class
            }
    )
    public void handleGeneralException(
            Exception e,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        LOGGER.error(e.getLocalizedMessage(), e);
    }

    /**
     * Метод, в котором осуществляется глобальная обработка исключений валидации данных
     *
     * @param e        Обрабатываемое исключение
     * @param response HTTP ответ, в который в случае выброса исключения добавляется информация об исключении
     * @throws IOException В случае, если возникает ошибка записи в response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidationException(
            MethodArgumentNotValidException e,
            HttpServletResponse response
    ) throws IOException {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put(
                    ((FieldError) error).getField(),
                    error.getDefaultMessage()
            );
            LOGGER.error(e.getLocalizedMessage(), e);
        });
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errors));
    }

    /**
     * Метод, в котором осуществляется глобальная обработка исключения NoResultException
     *
     * @param e Обрабатываемое исключение
     * @param response HTTP ответ, в который в случае выброса исключения добавляется информация об исключении
     * @throws IOException В случае, если возникает ошибка записи в response
     */
    @ExceptionHandler(NoResultException.class)
    public void handleNoResultException(NoResultException e, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        LOGGER.error(e.getLocalizedMessage(), e);
    }
}