package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.data.dto.SiteDto;
import ru.job4j.urlshortcut.data.dto.SiteRegistrationRequestDto;
import ru.job4j.urlshortcut.service.SiteService;

import javax.validation.Valid;

/**
 * Контроллер, реализующий доступ к объектам модели Site через REST
 */
@RestController
@AllArgsConstructor
public class SiteController {

    /**
     * Сервис, реализующий логику работы с объектами модели Site
     */
    private final SiteService siteService;

    /**
     * Регистрация нового сайта в системе
     *
     * @param requestDto Тело запроса на регистрацию нового сайта в системе
     * @return Данные зарегистрированного сайта, представленные в формате SiteDto
     */
    @PostMapping("/registration")
    public ResponseEntity<SiteDto> register(@RequestBody @Valid SiteRegistrationRequestDto requestDto) {
        return new ResponseEntity<>(
                siteService.register(requestDto.getSite()),
                HttpStatus.CREATED
        );
    }
}