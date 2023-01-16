package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.data.dto.UrlConversionRequestDto;
import ru.job4j.urlshortcut.data.dto.UrlConversionResponseDto;
import ru.job4j.urlshortcut.data.entity.Url;
import ru.job4j.urlshortcut.service.UrlService;

import javax.validation.Valid;

/**
 * Контроллер, реализующий доступ к объектам модели Url через REST
 */
@RestController
@AllArgsConstructor
public class UrlController {

    /**
     * Сервис, реализующий логику работы с объектами модели Url
     */
    private final UrlService urlService;

    /**
     * Сконвертировать передаваемый URL в сокращенный формат
     *
     * @return DTO, содержащий данные сокращенного URL
     */
    @PostMapping("/convert")
    public ResponseEntity<UrlConversionResponseDto> convert(
            @RequestBody @Valid UrlConversionRequestDto requestDto
    ) {
        Url url = urlService.convert(requestDto.getUrl());
        UrlConversionResponseDto responseDto = new UrlConversionResponseDto(url.getShortUrl());
        return new ResponseEntity<>(
                responseDto,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/redirect/{shortUrl}")
    public ResponseEntity<String> redirect(@PathVariable String shortUrl) {
        return new ResponseEntity<>(
                urlService.getFullUrlByShortUrl(shortUrl),
                HttpStatus.FOUND
        );
    }
}